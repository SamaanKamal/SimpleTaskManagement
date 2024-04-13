package com.example.Task.Management.Controller.Event;

import com.example.Task.Management.Configuration.ConnectionChecker;
import com.example.Task.Management.Entity.Event;
import com.example.Task.Management.Helpers.EventHelper.EventRequest;
import com.example.Task.Management.Helpers.EventHelper.EventResponse;
import com.example.Task.Management.Service.Event.EventService;
import com.google.api.client.util.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/api/Events")
public class EventController {
    private List<EventRequest> offlineEvents = new ArrayList<>();
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ConnectionChecker connectionChecker;
    @Autowired
    private EventService eventService;
    @GetMapping
    public ResponseEntity<EventResponse> fetchEvents(){
        eventService.syncDatabaseToCache();
        List<Event> events = eventService.getAllEvents();
        if(events==null)
        {
            return ResponseEntity.notFound().build();
        }
        EventResponse response = new EventResponse(events);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/{eventId}")
    public ResponseEntity<Event> fetchEvent(@PathVariable Integer eventId){
        eventService.syncDatabaseToCache();
        Event event = eventService.getEvent(eventId);
        if(event==null)
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(event);
    }

    @PostMapping("/createEvent")
    public ResponseEntity<String> createEvent(@RequestBody EventRequest eventRequest){
        if(connectionChecker.isInternetConnected())
        {
            if(eventRequest==null){
                return ResponseEntity.badRequest().body("Bad Request data");
            }
            boolean added =eventService.addEvent(eventRequest);
            if(!added){
                return ResponseEntity.internalServerError().body("There is a problem with adding using the api or the database");
            }
            return ResponseEntity.status(HttpStatus.CREATED).body("Event Data Created Successfully");
        }
        else {
            ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8080/api/Events/offlineCreation", eventRequest, String.class);
            return response;
        }

    }
    @PutMapping("updateEvent/{eventId}")
    public ResponseEntity<Event> updateEvent(@PathVariable Integer eventId, @RequestBody EventRequest eventRequest) {
        if(eventRequest==null|| eventId ==null){
            return ResponseEntity.badRequest().build();
        }
        Event updatedEvent =eventService.updateEvent(eventId, eventRequest);
        if(updatedEvent==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedEvent);
    }

    @DeleteMapping("deleteEvent/{eventId}")
    public ResponseEntity<String> deleteEvent(@PathVariable Integer eventId) {

        if(eventId==null){
            return ResponseEntity.badRequest().body("Bad Request data");
        }
        boolean deleted =eventService.deleteEvent(eventId);
        if(!deleted){
            return ResponseEntity.internalServerError().body("There is a problem with deleting using the id or from the database");
        }
        return ResponseEntity.ok().body("Event Data Deleted Successfully");
    }
    @GetMapping("/recover")
    public ResponseEntity<String> recoverDataFromDatabase(){
        boolean recovered =eventService.recovery();
        if(!recovered){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Database is either null or problem with the serve");
        }
        return  ResponseEntity.ok().body("Event Data Recovered  from the database");
    }
    @PostMapping("/offlineCreation")
    public ResponseEntity<String> createOfflineEvent(@RequestBody EventRequest eventRequest){
        boolean offline = offlineEvents.add(eventRequest);
        if(offline)
            return ResponseEntity.ok().body("Event data saved offline.");
        return ResponseEntity.ok("there is a problem with the request object");
    }
    @Scheduled(fixedRate = 300000) // Run every  5 minutes
    public void processOfflineEvents() {
        if (connectionChecker.isInternetConnected()) {
            syncOfflineEventsWithGoogleCalendar();
        }
    }
    private void syncOfflineEventsWithGoogleCalendar() {
        if (!offlineEvents.isEmpty()) {
            Iterator<EventRequest> iterator = offlineEvents.iterator();

            while (iterator.hasNext()) {
                EventRequest eventRequest = iterator.next();
                boolean added = eventService.addEventUsingApi(eventRequest.getSummary(), eventRequest.getLocation(), eventRequest.getDescription(), new DateTime(eventRequest.getStartDatetime()), new DateTime(eventRequest.getEndDatetime()));
                if (added) {
                    iterator.remove();
                }
            }
        }
    }
}
