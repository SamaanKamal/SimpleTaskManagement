package com.example.Task.Management.Service.DetailedServices.AttendeeService;

import com.example.Task.Management.Entity.Attachment;
import com.example.Task.Management.Entity.Attendee;
import com.example.Task.Management.Helpers.AttachmentHelper.AttachmentRequest;
import com.example.Task.Management.Helpers.AttendeeHelper.AttendeeRequest;

import java.util.List;

public interface IAttendeeService {
    List<Attendee> getAllAttendees();
    Attendee getAttendee(Integer attendeeId);
    void createAttendee(AttendeeRequest attendeeRequest);
    Attendee updateAttendee(Integer attendeeId,AttendeeRequest attendeeRequest);
    void deleteAttendee(Integer attendeeId);
    void addAttendees(List<AttendeeRequest> attendeeRequests);

}
