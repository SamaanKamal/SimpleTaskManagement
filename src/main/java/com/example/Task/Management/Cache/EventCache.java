package com.example.Task.Management.Cache;

import com.example.Task.Management.Entity.Event;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

@Component
public class EventCache {
    private final Map<Integer, Event> cache = new TreeMap<>();

    public void addEvent(Integer eventId, Event event) {
        if(cache.containsKey(eventId))
        {
            updateEvent(eventId,event);
        }
        else{
            cache.put(eventId, event);
        }

    }

    public void updateEvent(Integer eventId, Event event) {
        if (cache.containsKey(eventId)) {
            cache.put(eventId, event);
        }
    }

    public void deleteEvent(Integer eventId) {
        cache.remove(eventId);

    }

    public Event getEvent(Integer eventId) {
        return cache.get(eventId);
    }

    public Collection<Event> getAllEvents() {
        return cache.values();
    }
}
