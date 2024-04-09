package com.example.Task.Management.Controller.Event;

import com.example.Task.Management.Entity.Event;
import com.example.Task.Management.Helpers.EventHelper.EventRequest;
import com.example.Task.Management.Helpers.EventHelper.EventResponse;
import com.example.Task.Management.Service.Event.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Events")
public class EventController {
    @Autowired
    private IEventService eventService;
    @GetMapping
    public ResponseEntity<EventResponse> fetchEvents(){
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
        Event event = eventService.getEvent(eventId);
        if(event==null)
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(event);
    }

    @PostMapping("/createEvent")
    public ResponseEntity<String> createEvent(@RequestBody EventRequest eventRequest){
        if(eventRequest==null){
            return ResponseEntity.badRequest().body("Bad Request data");
        }
        boolean added =eventService.addEvent(eventRequest);
        if(!added){
            return ResponseEntity.internalServerError().body("There is a problem with adding using the api or the database");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Event Data Created Successfully");
    }
    @PutMapping("updateEvent/{eventId}")
    public ResponseEntity<Event> updateOrganizer(@PathVariable Integer eventId, @RequestBody EventRequest eventRequest) {
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
    public ResponseEntity<String> deleteOrganizer(@PathVariable Integer eventId) {
        if(eventId==null){
            return ResponseEntity.badRequest().body("Bad Request data");
        }
        boolean deleted =eventService.deleteEvent(eventId);
        if(!deleted){
            return ResponseEntity.internalServerError().body("There is a problem with deleting using the id or from the database");
        }
        return ResponseEntity.ok().body("Event Data Deleted Successfully");
    }
}
