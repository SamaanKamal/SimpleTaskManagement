package com.example.Task.Management.Configuration;

import org.springframework.stereotype.Component;

import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;

@Component
public class ConnectionChecker {
    public boolean isInternetConnected() {
        try {
            URL url = new URL("https://www.google.com");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("HEAD");
            connection.setConnectTimeout(1000); // Timeout in milliseconds
            int responseCode = connection.getResponseCode();
            return responseCode == HttpURLConnection.HTTP_OK;
        } catch (Exception e) {
            return false;
        }
    }
}
