package com.example.Task.Management.Service.Event;

import com.example.Task.Management.Cache.EventCache;
import com.example.Task.Management.Cache.EventSync;
import com.example.Task.Management.Configuration.ConnectionChecker;
import com.example.Task.Management.Entity.*;
import com.example.Task.Management.Helpers.EventHelper.EventRequest;
import com.example.Task.Management.Repository.EventRepository;
import com.example.Task.Management.Service.DetailedServices.CreatorService.ICreatorService;
import com.example.Task.Management.Service.DetailedServices.OrganizerService.IOrganizerService;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.EventReminder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class EventService implements  IEventService{

    @Autowired
    private ConnectionChecker connectionChecker;
    @Autowired
    private Calendar calendarService;

    @Value("${google.calendar.calendar-id}")
    private String calendarId;

    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private EventCache eventCache;
    @Autowired
    private EventSync eventSync;
    @Autowired
    private ICreatorService creatorService;
    @Autowired
    private IOrganizerService organizerService;

    @Override
    public List<Event> getAllEvents() {
        // check the cache first
        Collection<Event>eventCollection= eventCache.getAllEvents();
        List<Event> events = new ArrayList<>(eventCollection);
        if(events==null||events.isEmpty()){
            //if null get it from the database
            events =eventRepository.findAll();
            if(events==null){
                throw new RuntimeException("Could not find any Events");
            }
            else{
                // add events to the cache
                for(Event event:events){
                    eventCache.addEvent(event.getEventId(),event);
                }
            }
        }

        return events;
    }

    @Override
    public Event getEvent(Integer eventId) {
        //check the cache first
        Event event = eventCache.getEvent(eventId);
        if(event==null){
            // if null get it from the database
            event =eventRepository.findById(eventId).orElseThrow(()->
                    new RuntimeException("Event not found with id:"  + eventId));
            // add it to the cache
            eventCache.addEvent(eventId,event);
        }
        return event;
    }

    @Override
    public boolean addEvent(EventRequest eventRequest) {
        Event event = new Event();
        boolean databaseEvent =false;
        setBasicEventFields(event,eventRequest);
        event.setCreationTime(LocalDateTime.now());

        List<Attendee> attendees = new ArrayList<>();
        for(Attendee attendee:eventRequest.getAttendees()){
            Attendee attendee1 = new Attendee(attendee.getEmail(),attendee.getDisplayName(),attendee.isResource(),
                    attendee.isOptional(),attendee.getResponseStatus(),attendee.getComment(),attendee.getAdditionalGuests(),attendee.isSelf());
            attendees.add(attendee1);
        }
        event.setAttendees(attendees);

        List<Attachment> attachments = new ArrayList<>();
        for(Attachment attachment:eventRequest.getAttachments()){
            Attachment attachment1 = new Attachment(attachment.getFileUrl(), attachment.getTitle(), attachment.getMimetype(), attachment.getIconLink(),attachment.getFileId());
            attachments.add(attachment1);
        }
        event.setAttachments(attachments);

        event.setCreator(eventRequest.getCreator());
        event.setOrganizer(eventRequest.getOrganizer());

        Event returnedEvent = eventRepository.save(event);
        if(returnedEvent!=null)
            databaseEvent=true;

        // adding event into the cache
        eventCache.addEvent(returnedEvent.getEventId(),returnedEvent);
        System.out.println("here");

        DateTime startTime = new DateTime(eventRequest.getStartDatetime());
        DateTime endTime = new DateTime(eventRequest.getEndDatetime());

        System.out.println(connectionChecker.isInternetConnected());
        // creating event using the api
        if(connectionChecker.isInternetConnected())
        {
            boolean APIEvent = addEventUsingApi(eventRequest.getSummary(),eventRequest.getLocation(),eventRequest.getDescription(),startTime,endTime);
        }

        if(databaseEvent)
            return true;

        return false;
    }

    @Override
    public Event updateEvent(Integer eventId, EventRequest eventRequest) {
        Event event =eventRepository.findById(eventId).orElseThrow(()->
                new RuntimeException("Event not found with id:"  + eventId));
        setBasicEventFields(event,eventRequest);
        event.setUpdatedTime(LocalDateTime.now());

        updateAttachments(event,eventRequest.getAttachments());
        updateAttendees(event,eventRequest.getAttendees());
        Creator creator = creatorService.getCreator(eventRequest.getCreator().getCreatorId());
        event.setCreator(creator);
        Organizer organizer = organizerService.getOrganizer(eventRequest.getOrganizer().getOrganizerId());
        event.setOrganizer(organizer);

        Event returnedEvent = eventRepository.save(event);
        eventCache.updateEvent(eventId,returnedEvent);
        return returnedEvent;
    }

    @Override
    public boolean deleteEvent(Integer eventId) {
        Event event =eventRepository.findById(eventId).orElseThrow(()->
                new RuntimeException("Event not found with id:"  + eventId));
        if(event!=null){
            eventRepository.delete(event);
            eventCache.deleteEvent(eventId);
            return true;
        }

        return false;
    }

    public boolean addEventUsingApi(String summary,String location,String description,DateTime start , DateTime end){
        // creating new Event
        TimeZone timeZone = TimeZone.getTimeZone("Africa/Cairo");
        com.google.api.services.calendar.model.Event event = new com.google.api.services.calendar.model.Event()
                .setSummary(summary)
                .setLocation(location)
                .setDescription(description);


        // setting the start date and the end date
        event.setStart(new EventDateTime()
                .setDateTime(start)
                .setTimeZone(timeZone.getID()));

        event.setEnd(new EventDateTime()
                .setDateTime(end)
                .setTimeZone(timeZone.getID()));

        // Reminders
        EventReminder[] reminders = new EventReminder[] {
                new EventReminder().setMethod("email").setMinutes(24 * 60),
                new EventReminder().setMethod("popup").setMinutes(10)
        };
        event.setReminders(new com.google.api.services.calendar.model.Event.Reminders().setUseDefault(false).setOverrides(Arrays.asList(reminders)));

//        // Recurrence
//        String[] recurrence = {"RRULE:FREQ=DAILY;COUNT=2"};
//        event.setRecurrence(Arrays.asList(recurrence));

        try {
            calendarService.events().insert(calendarId, event).execute();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    // Synchronization: Cache to Database
    public void syncCacheToDatabase() {
        eventSync.syncCacheToDatabase();
    }

    // Synchronization: Database to Cache
    public void syncDatabaseToCache() {
        eventSync.syncDatabaseToCache();
    }
    // Scheduled cache synchronization task (runs every hour)
    @Scheduled(fixedRate = 3600000) // 3600000 ms = 1 hour
    public void scheduledCacheSynchronization() {
        eventSync.syncCacheToDatabase();
    }
    private void setBasicEventFields(Event event, EventRequest eventRequest) {
        event.setSummary(eventRequest.getSummary());
        event.setDescription(eventRequest.getDescription());
        event.setStatus(eventRequest.getStatus());
        event.setLocation(eventRequest.getLocation());
        event.setVisibility(eventRequest.getVisibility());
        event.setStartDatetime(eventRequest.getStartDatetime());
        event.setEndDatetime(eventRequest.getEndDatetime());
    }

    private void updateAttachments(Event event, List<Attachment> attachments) {
        List<Attachment> updatedAttachments = new ArrayList<>();

        for (Attachment attachmentItem : attachments) {
            if (attachmentItem.getId() != null) {
                Attachment existingAttachment = event.getAttachments().stream()
                        .filter(attachment -> attachment.getId().equals(attachmentItem.getId()))
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("Attachment not found with id:" + attachmentItem.getId()));

                existingAttachment.setFileUrl(attachmentItem.getFileUrl());
                existingAttachment.setTitle(attachmentItem.getTitle());
                existingAttachment.setMimetype(attachmentItem.getMimetype());
                existingAttachment.setIconLink(attachmentItem.getIconLink());
                existingAttachment.setFileId(attachmentItem.getFileId());

                updatedAttachments.add(existingAttachment);
            } else {
                Attachment newAttachment = new Attachment();
                newAttachment.setFileUrl(attachmentItem.getFileUrl());
                newAttachment.setTitle(attachmentItem.getTitle());
                newAttachment.setMimetype(attachmentItem.getMimetype());
                newAttachment.setIconLink(attachmentItem.getIconLink());
                newAttachment.setFileId(attachmentItem.getFileId());

                updatedAttachments.add(newAttachment);
            }
        }

        event.setAttachments(updatedAttachments);
    }
    private void updateAttendees(Event event, List<Attendee> attendees) {
        List<Attendee> updatedAttendees = new ArrayList<>();

        for (Attendee attendeeItem : attendees) {
            if (attendeeItem.getAttendeeId() != null) {

                Attendee existingAttendee = event.getAttendees().stream()
                        .filter(attendee -> attendee.getAttendeeId().equals(attendeeItem.getAttendeeId()))
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("Attachment not found with id: " + attendeeItem.getAttendeeId()));

                existingAttendee.setEmail(attendeeItem.getEmail());
                existingAttendee.setDisplayName(attendeeItem.getDisplayName());
                existingAttendee.setComment(attendeeItem.getComment());
                existingAttendee.setResponseStatus(attendeeItem.getResponseStatus());
                existingAttendee.setResource(attendeeItem.isResource());
                existingAttendee.setOptional(attendeeItem.isOptional());
                existingAttendee.setAdditionalGuests(attendeeItem.getAdditionalGuests());
                existingAttendee.setSelf(attendeeItem.isSelf());



                updatedAttendees.add(existingAttendee);
            } else {
                Attendee newAttendee = new Attendee();
                newAttendee.setEmail(attendeeItem.getEmail());
                newAttendee.setDisplayName(attendeeItem.getDisplayName());
                newAttendee.setComment(attendeeItem.getComment());
                newAttendee.setResponseStatus(attendeeItem.getResponseStatus());
                newAttendee.setResource(attendeeItem.isResource());
                newAttendee.setOptional(attendeeItem.isOptional());
                newAttendee.setAdditionalGuests(attendeeItem.getAdditionalGuests());
                newAttendee.setSelf(attendeeItem.isSelf());


                updatedAttendees.add(newAttendee);
            }
        }

        event.setAttendees(updatedAttendees);
    }

    public boolean recovery(){
        syncDatabaseToCache();
        Collection<Event>eventCollection= eventCache.getAllEvents();
        List<Event> events = new ArrayList<>(eventCollection);
        if(events==null||events.isEmpty()){
            return false;
        }
        return true;
    }

}
