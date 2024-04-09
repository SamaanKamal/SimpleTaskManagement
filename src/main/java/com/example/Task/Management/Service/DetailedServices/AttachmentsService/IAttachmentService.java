package com.example.Task.Management.Service.DetailedServices.AttachmentsService;

import com.example.Task.Management.Entity.Attachment;
import com.example.Task.Management.Entity.Event;
import com.example.Task.Management.Helpers.AttachmentHelper.AttachmentRequest;

import java.util.List;

public interface IAttachmentService {
    List<Attachment> getAllAttachments();

    Attachment getAttachment(Integer attachmentId);
    void createAttachment(AttachmentRequest attachmentRequest);
    void updateAttachment(Integer attachmentId, AttachmentRequest attachmentRequest);
    void deleteAttachment(Integer attachmentId);
    public void addAttachments(List<AttachmentRequest> attachmentRequests );




}
