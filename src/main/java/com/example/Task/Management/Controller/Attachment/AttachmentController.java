package com.example.Task.Management.Controller.Attachment;

import com.example.Task.Management.Entity.Attachment;
import com.example.Task.Management.Helpers.AttachmentHelper.AttachmentRequest;
import com.example.Task.Management.Helpers.AttachmentHelper.AttachmentResponse;
import com.example.Task.Management.Service.DetailedServices.AttachmentsService.IAttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Attachments")
public class AttachmentController {
    @Autowired
    private IAttachmentService attachmentService;


    @GetMapping
    public ResponseEntity<AttachmentResponse> fetchAttachments(){
        List<Attachment>  attachments = attachmentService.getAllAttachments();
        if(attachments==null)
        {
            return ResponseEntity.notFound().build();
        }
        AttachmentResponse response = new AttachmentResponse(attachments);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/{attachmentId}")
    public ResponseEntity<Attachment> fetchAttachment(@PathVariable Integer attachmentId){
        Attachment attachment = attachmentService.getAttachment(attachmentId);
        if(attachment==null)
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(attachment);
    }

    @PostMapping("/createAttachment")
    public ResponseEntity<String> createAttachment(@RequestBody AttachmentRequest attachmentRequest){
        if(attachmentRequest==null){
            return ResponseEntity.badRequest().body("Bad Request data");
        }
        attachmentService.createAttachment(attachmentRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("Attachment Data Created Successfully");
    }
    @PutMapping("updateAttachment/{attachmentId}")
    public ResponseEntity<String> updateAttachment(@PathVariable Integer attachmentId, @RequestBody AttachmentRequest attachmentRequest) {
        if(attachmentRequest==null|| attachmentId ==null){
            return ResponseEntity.badRequest().body("Bad Request data");
        }
        attachmentService.updateAttachment(attachmentId, attachmentRequest);
        return ResponseEntity.ok().body("Attachment data Updated successfully");
    }

    @DeleteMapping("deleteAttachment/{attachmentId}")
    public ResponseEntity<String> deleteAttachment(@PathVariable Integer attachmentId) {
        if(attachmentId==null){
            return ResponseEntity.badRequest().body("Bad Request data");
        }
        attachmentService.deleteAttachment(attachmentId);
        return ResponseEntity.ok().body("Attachment Data Deleted Successfully");
    }
}
