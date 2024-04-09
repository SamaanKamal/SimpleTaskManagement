package com.example.Task.Management.Helpers.EventHelper;

import com.example.Task.Management.Entity.Attachment;
import com.example.Task.Management.Entity.Attendee;
import com.example.Task.Management.Entity.Creator;
import com.example.Task.Management.Entity.Organizer;


import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class EventRequest {
    private String summary;
    private String description;

    private Date startDatetime;

    private Date endDatetime;

    private String location;
    private String status;
    private String visibility;

    private Organizer organizer;

    private Creator creator;

    private LocalDateTime creationTime;

    private LocalDateTime updatedTime;

    private List<Attendee> attendees;

    private List<Attachment> attachments;

    public EventRequest() {
    }

    public EventRequest(String summary, String description, Date startDatetime, Date endDatetime, String location, String status, String visibility, Organizer organizer, Creator creator, LocalDateTime creationTime, LocalDateTime updatedTime, List<Attendee> attendees, List<Attachment> attachments) {
        this.summary = summary;
        this.description = description;
        this.startDatetime = startDatetime;
        this.endDatetime = endDatetime;
        this.location = location;
        this.status = status;
        this.visibility = visibility;
        this.organizer = organizer;
        this.creator = creator;
        this.creationTime = creationTime;
        this.updatedTime = updatedTime;
        this.attendees = attendees;
        this.attachments = attachments;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDatetime() {
        return startDatetime;
    }

    public void setStartDatetime(Date startDatetime) {
        this.startDatetime = startDatetime;
    }

    public Date getEndDatetime() {
        return endDatetime;
    }

    public void setEndDatetime(Date endDatetime) {
        this.endDatetime = endDatetime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public Organizer getOrganizer() {
        return organizer;
    }

    public void setOrganizer(Organizer organizer) {
        this.organizer = organizer;
    }

    public Creator getCreator() {
        return creator;
    }

    public void setCreator(Creator creator) {
        this.creator = creator;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public LocalDateTime getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(LocalDateTime updatedTime) {
        this.updatedTime = updatedTime;
    }

    public List<Attendee> getAttendees() {
        return attendees;
    }

    public void setAttendees(List<Attendee> attendees) {
        this.attendees = attendees;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }
}
