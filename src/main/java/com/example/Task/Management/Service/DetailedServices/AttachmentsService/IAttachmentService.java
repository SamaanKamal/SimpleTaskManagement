package com.example.Task.Management.Service.DetailedServices.AttachmentsService;

import com.example.Task.Management.Entity.Attachment;
import com.example.Task.Management.Entity.Event;

import java.util.List;

public interface IAttachmentService {
    List<Attachment> getAllAttachments();

    String createEvent(Event event);
    String updateEvent(Integer eventId, Event event);
    String deleteEvent(Integer eventId);

}
