package com.example.Task.Management.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "attachments")
public class Attachment {
    @Column(name = "file_Url")
    private String fileUrl;
    @Column(name = "title")
    private String title;
    @Column(name = "mime_type")
    private String mimetype;
    @Column(name = "icon_Link")
    private String iconLink;
    @Column(name = "file_Id")
    private String fileId;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    public Attachment() {
    }

    public Attachment(String fileUrl, String title, String mimetype, String iconLink, String fileId) {
        this.fileUrl = fileUrl;
        this.title = title;
        this.mimetype = mimetype;
        this.iconLink = iconLink;
        this.fileId = fileId;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMimetype() {
        return mimetype;
    }

    public void setMimetype(String mimetype) {
        this.mimetype = mimetype;
    }

    public String getIconLink() {
        return iconLink;
    }

    public void setIconLink(String iconLink) {
        this.iconLink = iconLink;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
