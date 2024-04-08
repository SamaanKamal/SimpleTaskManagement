package com.example.Task.Management.Helpers.AttachmentHelper;

import jakarta.persistence.Column;

public class AttachmentRequest {
    private String fileUrl;
    private String title;
    private String mimetype;
    private String iconLink;
    private String fileId;

    public AttachmentRequest() {
    }

    public AttachmentRequest(String fileUrl, String title, String mimetype, String iconLink, String fileId) {
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
}
