package com.example.Task.Management.Configuration;

import org.springframework.stereotype.Component;

import java.net.InetAddress;

@Component
public class ConnectionChecker {
    public boolean isInternetConnected() {
        try {
            InetAddress address = InetAddress.getLoopbackAddress();
            return address.isReachable(1000);
        } catch (Exception e) {
            return false;
        }
    }
}
