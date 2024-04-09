package com.example.Task.Management.Service.DetailedServices.AttendeeService;


import com.example.Task.Management.Entity.Attendee;
import com.example.Task.Management.Helpers.AttendeeHelper.AttendeeRequest;
import com.example.Task.Management.Repository.AttendeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AttendeeService implements IAttendeeService {
    @Autowired
    private AttendeeRepository attendeeRepository;
    @Override
    public List<Attendee> getAllAttendees() {
        return attendeeRepository.findAll();
    }

    @Override
    public Attendee getAttendee(Integer attendeeId) {
        return attendeeRepository.findById(attendeeId).orElseThrow(()->
                new RuntimeException("Attendee not found with id: " + attendeeId));
    }

    @Override
    public void createAttendee(AttendeeRequest attendeeRequest) {
        Attendee attendee = new Attendee();
        attendee.setEmail(attendeeRequest.getEmail());
        attendee.setComment(attendeeRequest.getComment());
        attendee.setAdditionalGuests(attendeeRequest.getAdditionalGuests());
        attendee.setDisplayName(attendeeRequest.getDisplayName());
        attendee.setOptional(attendeeRequest.isOptional());
        attendee.setResponseStatus(attendeeRequest.getResponseStatus());
        attendee.setResource(attendeeRequest.isResource());
        attendee.setSelf(attendeeRequest.isSelf());
        attendeeRepository.save(attendee);
    }

    @Override
    public Attendee updateAttendee(Integer attendeeId, AttendeeRequest attendeeRequest) {
        Attendee attendee =attendeeRepository.findById(attendeeId).orElseThrow(()->
                new RuntimeException("Attendee not found with id: " + attendeeId));
        attendee.setEmail(attendeeRequest.getEmail());
        attendee.setComment(attendeeRequest.getComment());
        attendee.setAdditionalGuests(attendeeRequest.getAdditionalGuests());
        attendee.setDisplayName(attendeeRequest.getDisplayName());
        attendee.setOptional(attendeeRequest.isOptional());
        attendee.setResponseStatus(attendeeRequest.getResponseStatus());
        attendee.setResource(attendeeRequest.isResource());
        attendee.setSelf(attendeeRequest.isSelf());
        Attendee updatedAttendee = attendeeRepository.save(attendee);
        return updatedAttendee;
    }

    @Override
    public void deleteAttendee(Integer attendeeId) {
        Attendee attendee =attendeeRepository.findById(attendeeId).orElseThrow(()->
                new RuntimeException("Attendee not found with id: " + attendeeId));
        attendeeRepository.delete(attendee);
    }

    @Override
    public void addAttendees(List<AttendeeRequest> attendeeRequests) {
        List<Attendee> attendees = new ArrayList<>();
        for (AttendeeRequest request : attendeeRequests) {
            Attendee attendee  = new Attendee();
            attendee.setEmail(request.getEmail());
            attendee.setComment(request.getComment());
            attendee.setAdditionalGuests(request.getAdditionalGuests());
            attendee.setDisplayName(request.getDisplayName());
            attendee.setOptional(request.isOptional());
            attendee.setResponseStatus(request.getResponseStatus());
            attendee.setResource(request.isResource());
            attendee.setSelf(request.isSelf());
            attendees.add(attendee);
        }
        attendeeRepository.saveAll(attendees);
    }
}
