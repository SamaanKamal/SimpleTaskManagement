package com.example.Task.Management.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "attachment")
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
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

//    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
//    @JoinColumn(name = "event_id")
//    private Event event;

    public Attachment() {
    }

    public Attachment(String fileUrl, String title, String mimetype, String iconLink, String fileId) {
        this.fileUrl = fileUrl;
        this.title = title;
        this.mimetype = mimetype;
        this.iconLink = iconLink;
        this.fileId = fileId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

//    public Event getEvent() {
//        return event;
//    }
//
//    public void setEvent(Event event) {
//        this.event = event;
//    }
}
