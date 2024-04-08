package com.example.Task.Management.Controller;

import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Events;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.GeneralSecurityException;

@RestController
public class TestAPIController {
    @Autowired
    private Calendar calendarService;

    @Value("${google.calendar.calendar-id}")
    private String calendarId;

    @GetMapping("/")
    public String getCalendarEvents() throws IOException, GeneralSecurityException {
        // Get upcoming events from the calendar
        Events events = calendarService.events().list(calendarId)
                .setMaxResults(10)
                .setTimeMin(new com.google.api.client.util.DateTime(System.currentTimeMillis()))
                .setOrderBy("startTime")
                .setSingleEvents(true)
                .execute();

        if (events.getItems().isEmpty()) {
            return "No upcoming events found.";
        } else {
            return "Events: " + events.getItems();
        }
    }
}
