package com.example.Task.Management.Helpers.AttendeeHelper;

import jakarta.persistence.Column;

public class AttendeeRequest {
    private String email;
    private String displayName;
    private boolean resource;
    private boolean optional;
    private String responseStatus;
    private String comment;
    private int additionalGuests;
    private boolean self;

    public AttendeeRequest() {
    }

    public AttendeeRequest(String email, String displayName, boolean resource, boolean optional, String responseStatus, String comment, int additionalGuests, boolean self) {
        this.email = email;
        this.displayName = displayName;
        this.resource = resource;
        this.optional = optional;
        this.responseStatus = responseStatus;
        this.comment = comment;
        this.additionalGuests = additionalGuests;
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

    public boolean isResource() {
        return resource;
    }

    public void setResource(boolean resource) {
        this.resource = resource;
    }

    public boolean isOptional() {
        return optional;
    }

    public void setOptional(boolean optional) {
        this.optional = optional;
    }

    public String getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(String responseStatus) {
        this.responseStatus = responseStatus;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getAdditionalGuests() {
        return additionalGuests;
    }

    public void setAdditionalGuests(int additionalGuests) {
        this.additionalGuests = additionalGuests;
    }

    public boolean isSelf() {
        return self;
    }

    public void setSelf(boolean self) {
        this.self = self;
    }

}
