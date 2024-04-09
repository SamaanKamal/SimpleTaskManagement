package com.example.Task.Management.Helpers.AttachmentHelper;

import com.example.Task.Management.Entity.Attachment;

import java.util.List;

public class AttachmentResponse {
    List<Attachment> attachments;

    public AttachmentResponse() {
    }

    public AttachmentResponse(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }
}
