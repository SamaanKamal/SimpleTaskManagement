package com.example.Task.Management.Helpers.EventHelper;

import com.example.Task.Management.Entity.Event;

import java.util.List;

public class EventResponse {
    List<Event> events;

    public EventResponse() {
    }

    public EventResponse(List<Event> events) {
        this.events = events;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
