package com.example.Task.Management.Configuration;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;

@Configuration
public class GoogleCalendarConfiguration {
    @Value("${google.calendar.service-account-key-file}")
    private String serviceAccountKeyFile;

    @Value("${spring.application.name}")
    private  String appName;
    @Bean
    public Calendar calendarService() throws IOException {
        // Load the service account credentials from JSON key file
        GoogleCredential credential = GoogleCredential.fromStream(
                         new FileInputStream(serviceAccountKeyFile))
                .createScoped(Collections.singleton(CalendarScopes.CALENDAR_READONLY));

        // Build and return a Google Calendar service
        return new Calendar.Builder(credential.getTransport(), credential.getJsonFactory(), null)
                .setApplicationName(appName)
                .build();
    }
}
