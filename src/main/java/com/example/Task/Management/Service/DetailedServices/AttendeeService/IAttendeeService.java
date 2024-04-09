package com.example.Task.Management.Service.DetailedServices.AttendeeService;

import com.example.Task.Management.Entity.Attachment;
import com.example.Task.Management.Entity.Attendee;
import com.example.Task.Management.Helpers.AttachmentHelper.AttachmentRequest;

import java.util.List;

public interface IAttendeeService {
    List<Attendee> getAllAttendees();
    Attendee getAttendee(Integer attendeeId);
    void createAttendee();
    void updateAttendee(Integer attendeeId);
    void deleteAttendee(Integer attendeeId);
    void addAttendees();

}
