package com.example.Task.Management.Configuration;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

@Configuration
public class GoogleCalendarConfiguration {
    @Value("${google.calendar.service-account-key-file}")
    private String serviceAccountKeyFile;

    @Value("${spring.application.name}")
    private  String appName;
    @Bean
    public Calendar calendarService() throws IOException, GeneralSecurityException {
        // Load the service account credentials from JSON key file
        GoogleCredential credential = GoogleCredential.fromStream(
                         new FileInputStream(serviceAccountKeyFile))
                .createScoped(Collections.singleton(CalendarScopes.CALENDAR));
        HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();

        // Build and return a Google Calendar service
        return new Calendar.Builder(httpTransport, jsonFactory,credential)
                .setApplicationName(appName)
                .build();
    }
}
