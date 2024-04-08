package com.example.Task.Management.Cache;

import com.example.Task.Management.Repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EventSync {
    @Autowired
    private EventCache cache;
    @Autowired
    private EventRepository repository;


    // Synchronize Cache to Database
    public void syncCacheToDatabase() {
        cache.getAllEvents().forEach(event -> {
            repository.save(event);
        });
    }

    // Synchronize Database to Cache
    public void syncDatabaseToCache() {
        repository.findAll().forEach(event -> {
            cache.addEvent(event.getEventId(), event);
        });
    }
}
