package com.example.Task.Management.Service.Event;

import com.example.Task.Management.Cache.EventCache;
import com.example.Task.Management.Cache.EventSync;
import com.example.Task.Management.Entity.Event;
import com.example.Task.Management.Helpers.EventHelper.EventRequest;
import com.example.Task.Management.Repository.EventRepository;
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
import java.util.Arrays;
import java.util.List;
import java.util.TimeZone;

@Service
public class EventService implements  IEventService{
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

    @Override
    public List<Event> getAllEvents() {
        // check the cache first
        List<Event> events = (List<Event>) eventCache.getAllEvents();
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
        event.setSummary(eventRequest.getSummary());
        event.setDescription(eventRequest.getDescription());
        event.setCreationTime(LocalDateTime.now());
        event.setStatus(eventRequest.getStatus());
        event.setDescription(eventRequest.getDescription());
        event.setLocation(eventRequest.getLocation());
        event.setVisibility(eventRequest.getVisibility());
        event.setStartDatetime(eventRequest.getStartDatetime());
        event.setEndDatetime(eventRequest.getEndDatetime());
        event.setAttendees(eventRequest.getAttendees());
        event.setAttachments(eventRequest.getAttachments());
        event.setCreator(eventRequest.getCreator());
        event.setOrganizer(eventRequest.getOrganizer());
        Event returnedEvent = eventRepository.save(event);
        if(returnedEvent!=null)
            databaseEvent=true;

        // adding event into the cache
        eventCache.addEvent(returnedEvent.getEventId(),returnedEvent);

        DateTime startTime = new DateTime(eventRequest.getStartDatetime());
        DateTime endTime = new DateTime(eventRequest.getEndDatetime());

        // creating event using the api
        boolean APIEvent = addEventUsingApi(eventRequest.getSummary(),eventRequest.getLocation(),eventRequest.getDescription(),startTime,endTime);
        if(databaseEvent&&APIEvent)
            return true;


        return false;
    }

    @Override
    public Event updateEvent(Integer eventId, EventRequest eventRequest) {
        Event event =eventRepository.findById(eventId).orElseThrow(()->
                new RuntimeException("Event not found with id:"  + eventId));
        event.setSummary(eventRequest.getSummary());
        event.setDescription(eventRequest.getDescription());
        event.setCreationTime(LocalDateTime.now());
        event.setStatus(eventRequest.getStatus());
        event.setDescription(eventRequest.getDescription());
        event.setLocation(eventRequest.getLocation());
        event.setVisibility(eventRequest.getVisibility());
        event.setStartDatetime(eventRequest.getStartDatetime());
        event.setEndDatetime(eventRequest.getEndDatetime());
        event.setAttendees(eventRequest.getAttendees());
        event.setAttachments(eventRequest.getAttachments());
        event.setCreator(eventRequest.getCreator());
        event.setOrganizer(eventRequest.getOrganizer());
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

    private boolean addEventUsingApi(String summary,String location,String description,DateTime start , DateTime end){
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

}
