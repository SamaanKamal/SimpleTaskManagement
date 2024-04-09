package com.example.Task.Management.Controller.Attendee;


import com.example.Task.Management.Entity.Attendee;
import com.example.Task.Management.Helpers.AttendeeHelper.AttendeeRequest;
import com.example.Task.Management.Helpers.AttendeeHelper.AttendeeResponse;
import com.example.Task.Management.Service.DetailedServices.AttendeeService.IAttendeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Attendees")
public class AttendeeController {
    @Autowired
    private IAttendeeService attendeeService;
    @GetMapping
    public ResponseEntity<AttendeeResponse> fetchAttendees(){
        List<Attendee> attendees = attendeeService.getAllAttendees();
        if(attendees==null)
        {
            return ResponseEntity.notFound().build();
        }
        AttendeeResponse response = new AttendeeResponse(attendees);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/{attendeeId}")
    public ResponseEntity<Attendee> fetchAttendee(@PathVariable Integer attendeeId){
        Attendee attendee = attendeeService.getAttendee(attendeeId);
        if(attendee==null)
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(attendee);
    }

    @PostMapping("/createAttendee")
    public ResponseEntity<String> createAttendee(@RequestBody AttendeeRequest attendeeRequest){
        if(attendeeRequest==null){
            return ResponseEntity.badRequest().body("Bad Request data");
        }
        attendeeService.createAttendee(attendeeRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("Attendee Data Created Successfully");
    }
    @PutMapping("updateAttendee/{attendeeId}")
    public ResponseEntity<Attendee> updateAttendee(@PathVariable Integer attendeeId, @RequestBody AttendeeRequest attendeeRequest) {
        if(attendeeRequest==null|| attendeeId ==null){
            return ResponseEntity.badRequest().build();
        }
        Attendee updatedAttendee =attendeeService.updateAttendee(attendeeId, attendeeRequest);
        if (updatedAttendee == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedAttendee);
    }

    @DeleteMapping("deleteAttendee/{attendeeId}")
    public ResponseEntity<String> deleteAttendee(@PathVariable Integer attendeeId) {
        if(attendeeId==null){
            return ResponseEntity.badRequest().body("Bad Request data");
        }
        attendeeService.deleteAttendee(attendeeId);
        return ResponseEntity.ok().body("Attendee Data Deleted Successfully");
    }
}
