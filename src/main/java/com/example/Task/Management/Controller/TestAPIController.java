package com.example.Task.Management.Controller;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.TimeZone;
import java.util.stream.Collectors;

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
    @GetMapping("/createEvent")
    public String createEvent() throws IOException, GeneralSecurityException {
        // creating new Event
        TimeZone timeZone = TimeZone.getTimeZone("Africa/Cairo");
        Event event = new Event()
                .setSummary("My first event!")
                .setLocation("Cairo, Egypt")
                .setDescription("First event with Spring Boot!");


        // setting the start date and the end date
        event.setStart(new EventDateTime()
                .setDateTime(new DateTime("2024-04-08T20:28:00+02:00"))
                .setTimeZone(timeZone.getID()));

        event.setEnd(new EventDateTime()
                .setDateTime(new DateTime("2024-04-10T20:28:00+02:00"))
                .setTimeZone(timeZone.getID()));

        // Reminders
        EventReminder[] reminders = new EventReminder[] {
                new EventReminder().setMethod("email").setMinutes(24 * 60),
                new EventReminder().setMethod("popup").setMinutes(10)
        };
        event.setReminders(new Event.Reminders().setUseDefault(false).setOverrides(Arrays.asList(reminders)));

//        // Recurrence
//        String[] recurrence = {"RRULE:FREQ=DAILY;COUNT=2"};
//        event.setRecurrence(Arrays.asList(recurrence));

        try {
            calendarService.events().insert(calendarId, event).execute();
            return "Event successfully created!";
        } catch (IOException e) {
            e.printStackTrace();
            return "Error creating event: " + e.getMessage();
        }
    }
}
