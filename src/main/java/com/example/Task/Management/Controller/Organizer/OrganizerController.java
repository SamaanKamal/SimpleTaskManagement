package com.example.Task.Management.Controller.Organizer;

import com.example.Task.Management.Entity.Organizer;
import com.example.Task.Management.Helpers.OrganizerHelper.OrganizerRequest;
import com.example.Task.Management.Helpers.OrganizerHelper.OrganizerResponse;
import com.example.Task.Management.Service.DetailedServices.OrganizerService.IOrganizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Organizers")
public class OrganizerController {
    @Autowired
    private IOrganizerService organizerService;

    @GetMapping
    public ResponseEntity<OrganizerResponse> fetchOrganizers(){
        List<Organizer> organizers = organizerService.getAllOrganizers();
        if(organizers==null)
        {
            return ResponseEntity.notFound().build();
        }
        OrganizerResponse response = new OrganizerResponse(organizers);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/{organizerId}")
    public ResponseEntity<Organizer> fetchOrganizer(@PathVariable Integer organizerId){
        Organizer organizer = organizerService.getOrganizer(organizerId);
        if(organizer==null)
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(organizer);
    }

    @PostMapping("/createOrganizer")
    public ResponseEntity<String> createOrganizer(@RequestBody OrganizerRequest organizerRequest){
        if(organizerRequest==null){
            return ResponseEntity.badRequest().body("Bad Request data");
        }
        organizerService.createOrganizer(organizerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("Organizer Data Created Successfully");
    }
    @PutMapping("updateOrganizer/{organizerId}")
    public ResponseEntity<String> updateOrganizer(@PathVariable Integer organizerId, @RequestBody OrganizerRequest organizerRequest) {
        if(organizerRequest==null|| organizerId ==null){
            return ResponseEntity.badRequest().body("Bad Request data");
        }
        organizerService.updateOrganizer(organizerId, organizerRequest);
        return ResponseEntity.ok().body("Organizer data Updated successfully");
    }

    @DeleteMapping("deleteOrganizer/{organizerId}")
    public ResponseEntity<String> deleteOrganizer(@PathVariable Integer organizerId) {
        if(organizerId==null){
            return ResponseEntity.badRequest().body("Bad Request data");
        }
        organizerService.deleteOrganizer(organizerId);
        return ResponseEntity.ok().body("Organizer Data Deleted Successfully");
    }
}
