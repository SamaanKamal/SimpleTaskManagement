package com.example.Task.Management.Helpers.AttendeeHelper;

import com.example.Task.Management.Entity.Attendee;

import java.util.List;

public class AttendeeResponse {
    List<Attendee> attendees;

    public AttendeeResponse() {
    }

    public AttendeeResponse(List<Attendee> attendees) {
        this.attendees = attendees;
    }

    public List<Attendee> getAttendees() {
        return attendees;
    }

    public void setAttendees(List<Attendee> attendees) {
        this.attendees = attendees;
    }
}
