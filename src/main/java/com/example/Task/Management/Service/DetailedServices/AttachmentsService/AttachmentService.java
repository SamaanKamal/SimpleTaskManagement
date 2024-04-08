package com.example.Task.Management.Service.DetailedServices.AttachmentsService;

import com.example.Task.Management.Entity.Attachment;
import com.example.Task.Management.Helpers.AttachmentHelper.AttachmentRequest;
import com.example.Task.Management.Repository.AttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttachmentService implements IAttachmentService {
    @Autowired
    private AttachmentRepository attachmentRepository;

    @Override
    public List<Attachment> getAllAttachments() {
        return attachmentRepository.findAll();
    }

    @Override
    public Attachment getAttachment(Integer attachmentId) {
        return attachmentRepository.findById(attachmentId).orElseThrow(()->
                new RuntimeException("Attachment not found with id: " + attachmentId));
    }

    @Override
    public String createAttachment(AttachmentRequest attachmentRequest) {
        Attachment attachment = new Attachment();
        attachment.setFileId(attachmentRequest.getFileId());
        attachment.setFileUrl(attachmentRequest.getFileUrl());
        attachment.setMimetype(attachmentRequest.getMimetype());
        attachment.setTitle(attachmentRequest.getTitle());
        attachment.setIconLink(attachmentRequest.getIconLink());
        var savedAttachment =attachmentRepository.save(attachment);
        if(savedAttachment==null){
            return null;
        }
        return "Attachment Created Successfully";
    }

    @Override
    public String updateAttachment(Integer attachmentId, AttachmentRequest attachmentRequest) {
        Attachment attachment = attachmentRepository.findById(attachmentId).orElseThrow(()->
                new RuntimeException("Attachment not found with id: " + attachmentId));
        attachment.setFileId(attachmentRequest.getFileId());
        attachment.setFileUrl(attachmentRequest.getFileUrl());
        attachment.setMimetype(attachmentRequest.getMimetype());
        attachment.setTitle(attachmentRequest.getTitle());
        attachment.setIconLink(attachmentRequest.getIconLink());
        var updatedAttachment =attachmentRepository.save(attachment);
        if(updatedAttachment==null){
            return null;
        }
        return "Attachment Updated Successfully";
    }

    @Override
    public String deleteAttachment(Integer attachmentId) {
        Attachment attachment =attachmentRepository.findById(attachmentId).orElseThrow(() ->
                new RuntimeException("Attachment not found with id: " + attachmentId));;
        if(attachment!=null){
            attachmentRepository.delete(attachment);
            return "Attachment Deleted Successfully";
        }
        return null;
    }
}
