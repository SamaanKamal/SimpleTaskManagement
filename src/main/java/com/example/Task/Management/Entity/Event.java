package com.example.Task.Management.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Integer eventId;

    @Column(name = "summary")
    private String summary;
    @Column(name = "description")
    private String description;

    @Column(name = "start_datetime")
    private Date startDatetime;

    @Column(name = "end_datetime")
    private Date endDatetime;

    @Column(name = "location")
    private String location;
    @Column(name = "status")
    private String status;
    @Column(name = "visibility")
    private String visibility;

    @OneToOne
    @JoinColumn(name = "organizer_id")
    private Organizer organizer;

    @OneToOne
    @JoinColumn(name = "creator_id")
    private Creator creator;


    @Column(name = "creation_time")
    private final LocalDateTime creationTime = LocalDateTime.now();

    @Column(name = "updated_time")
    private final LocalDateTime updatedTime= LocalDateTime.now();

    @OneToMany(mappedBy = "event")
    private List<Attendee> attendees;

    @OneToMany(mappedBy = "event")
    private List<Attachment> attachments;

    public Event() {
    }

    public Event(String summary, String description, String location, String status, String visibility) {
        this.summary = summary;
        this.description = description;
        this.location = location;
        this.status = status;
        this.visibility = visibility;
    }

    public Integer getEventId() {
        return eventId;
    }

    public String getSummary() {
        return summary;
    }

    public String getDescription() {
        return description;
    }

    public Date getStartDatetime() {
        return startDatetime;
    }

    public Date getEndDatetime() {
        return endDatetime;
    }

    public String getLocation() {
        return location;
    }

    public String getStatus() {
        return status;
    }

    public String getVisibility() {
        return visibility;
    }

    public Organizer getOrganizer() {
        return organizer;
    }

    public Creator getCreator() {
        return creator;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public LocalDateTime getUpdatedTime() {
        return updatedTime;
    }

    public List<Attendee> getAttendees() {
        return attendees;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public void setOrganizer(Organizer organizer) {
        this.organizer = organizer;
    }

    public void setCreator(Creator creator) {
        this.creator = creator;
    }

    public void setAttendees(List<Attendee> attendees) {
        this.attendees = attendees;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }
}
