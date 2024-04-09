package com.example.Task.Management.Service.DetailedServices.AttachmentsService;

import com.example.Task.Management.Entity.Attachment;
import com.example.Task.Management.Helpers.AttachmentHelper.AttachmentRequest;
import com.example.Task.Management.Repository.AttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public void createAttachment(AttachmentRequest attachmentRequest) {
        Attachment attachment = new Attachment();
        attachment.setFileId(attachmentRequest.getFileId());
        attachment.setFileUrl(attachmentRequest.getFileUrl());
        attachment.setMimetype(attachmentRequest.getMimetype());
        attachment.setTitle(attachmentRequest.getTitle());
        attachment.setIconLink(attachmentRequest.getIconLink());
        attachmentRepository.save(attachment);
    }

    @Override
    public void updateAttachment(Integer attachmentId, AttachmentRequest attachmentRequest) {
        Attachment attachment = attachmentRepository.findById(attachmentId).orElseThrow(()->
                new RuntimeException("Attachment not found with id: " + attachmentId));
        attachment.setFileId(attachmentRequest.getFileId());
        attachment.setFileUrl(attachmentRequest.getFileUrl());
        attachment.setMimetype(attachmentRequest.getMimetype());
        attachment.setTitle(attachmentRequest.getTitle());
        attachment.setIconLink(attachmentRequest.getIconLink());
        attachmentRepository.save(attachment);
    }

    @Override
    public void deleteAttachment(Integer attachmentId) {
        Attachment attachment =attachmentRepository.findById(attachmentId).orElseThrow(() ->
                new RuntimeException("Attachment not found with id: " + attachmentId));;
        if(attachment!=null){
            attachmentRepository.delete(attachment);
        }
    }

    @Override
    public void addAttachments(List<AttachmentRequest> attachmentRequests) {
        List<Attachment> attachments = new ArrayList<>();
        for (AttachmentRequest request : attachmentRequests) {
            Attachment attachment = new Attachment();
            attachment.setFileUrl(request.getFileUrl());
            attachment.setTitle(request.getTitle());
            attachment.setMimetype(request.getMimetype());
            attachment.setIconLink(request.getIconLink());
            attachment.setFileId(request.getFileId());
            attachments.add(attachment);
        }
        attachmentRepository.saveAll(attachments);
    }
}
