package com.example.Task.Management.Service.Event;

import com.example.Task.Management.Entity.Event;
import com.example.Task.Management.Helpers.EventHelper.EventRequest;

import java.util.List;

public interface IEventService {
    List<Event> getAllEvents();
    Event getEvent(Integer eventId);
    boolean addEvent(EventRequest eventRequest);
    boolean UpdateEvent(Integer eventId,EventRequest eventRequest);
    boolean deleteEvent(Integer eventId);

}
