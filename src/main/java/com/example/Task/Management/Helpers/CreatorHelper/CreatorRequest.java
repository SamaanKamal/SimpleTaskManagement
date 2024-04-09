package com.example.Task.Management.Helpers.CreatorHelper;

import jakarta.persistence.Column;

public class CreatorRequest {
    private String email;
    private String displayName;
    private boolean self;

    public CreatorRequest() {
    }

    public CreatorRequest(String email, String displayName, boolean self) {
        this.email = email;
        this.displayName = displayName;
        this.self = self;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public boolean isSelf() {
        return self;
    }

    public void setSelf(boolean self) {
        this.self = self;
    }
}
