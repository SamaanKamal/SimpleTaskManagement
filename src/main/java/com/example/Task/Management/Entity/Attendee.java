package com.example.Task.Management.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Attendee")
public class Attendee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attendee_id")
    private Integer attendeeId;
    @Column(name = "email")
    private String email;
    @Column(name = "display_name")
    private String displayName;
    @Column(name = "resource")
    private boolean resource;
    @Column(name = "optional")
    private boolean optional;
    @Column(name = "response_Status")
    private String responseStatus;
    @Column(name = "comment")
    private String comment;
    @Column(name = "additional_Guests")
    private int additionalGuests;
    @Column(name = "self")
    private boolean self;
    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    public Attendee() {
    }

    public Attendee(String email, String displayName, boolean resource, boolean optional, String responseStatus, String comment, int additionalGuests, boolean self) {
        this.email = email;
        this.displayName = displayName;
        this.resource = resource;
        this.optional = optional;
        this.responseStatus = responseStatus;
        this.comment = comment;
        this.additionalGuests = additionalGuests;
        this.self = self;
    }

    public Integer getAttendeeId() {
        return attendeeId;
    }

    public void setAttendeeId(Integer attendeeId) {
        this.attendeeId = attendeeId;
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

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
