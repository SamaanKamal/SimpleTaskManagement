package com.example.Task.Management.Controller.Creator;

import com.example.Task.Management.Entity.Creator;
import com.example.Task.Management.Helpers.CreatorHelper.CreatorRequest;
import com.example.Task.Management.Helpers.CreatorHelper.CreatorResponse;
import com.example.Task.Management.Service.DetailedServices.CreatorService.ICreatorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Creators")
public class CreatorController {
    private ICreatorService creatorService;
    @GetMapping
    public ResponseEntity<CreatorResponse> fetchCreators(){
        List<Creator> creators = creatorService.getAllCreators();
        if(creators==null)
        {
            return ResponseEntity.notFound().build();
        }
        CreatorResponse response = new CreatorResponse(creators);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/{creatorId}")
    public ResponseEntity<Creator> fetchCreator(@PathVariable Integer creatorId){
        Creator creator = creatorService.getCreator(creatorId);
        if(creator==null)
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(creator);
    }

    @PostMapping("/createCreator")
    public ResponseEntity<String> createCreator(@RequestBody CreatorRequest creatorRequest){
        if(creatorRequest==null){
            return ResponseEntity.badRequest().body("Bad Request data");
        }
        creatorService.createCreator(creatorRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("Creator Data Created Successfully");
    }
    @PutMapping("/{creatorId}")
    public ResponseEntity<String> updateCreator(@PathVariable Integer creatorId, @RequestBody CreatorRequest creatorRequest) {
        if(creatorRequest==null|| creatorId ==null){
            return ResponseEntity.badRequest().body("Bad Request data");
        }
        creatorService.updateCreator(creatorId, creatorRequest);
        return ResponseEntity.ok().body("Creator data Updated successfully");
    }

    @DeleteMapping("/{creatorId}")
    public ResponseEntity<String> deleteCreator(@PathVariable Integer creatorId) {
        if(creatorId==null){
            return ResponseEntity.badRequest().body("Bad Request data");
        }
        creatorService.deleteCreator(creatorId);
        return ResponseEntity.ok().body("Creator Data Deleted Successfully");
    }
}
