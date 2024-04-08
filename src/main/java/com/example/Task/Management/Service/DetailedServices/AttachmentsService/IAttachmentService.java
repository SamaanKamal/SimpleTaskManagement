package com.example.Task.Management.Service.DetailedServices.AttachmentsService;

import com.example.Task.Management.Entity.Attachment;
import com.example.Task.Management.Entity.Event;
import com.example.Task.Management.Helpers.AttachmentHelper.AttachmentRequest;

import java.util.List;

public interface IAttachmentService {
    List<Attachment> getAllAttachments();

    Attachment getAttachment(Integer attachmentId);
    String createAttachment(AttachmentRequest attachmentRequest);
    String updateAttachment(Integer attachmentId, AttachmentRequest attachmentRequest);
    String deleteAttachment(Integer attachmentId);




}
