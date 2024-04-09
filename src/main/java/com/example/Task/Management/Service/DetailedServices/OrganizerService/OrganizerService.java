package com.example.Task.Management.Service.DetailedServices.OrganizerService;

import com.example.Task.Management.Entity.Organizer;
import com.example.Task.Management.Helpers.OrganizerHelper.OrganizerRequest;
import com.example.Task.Management.Repository.OrganizerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizerService implements IOrganizerService{
    @Autowired
    private OrganizerRepository organizerRepository;


    @Override
    public List<Organizer> getAllOrganizers() {
        return organizerRepository.findAll();
    }

    @Override
    public Organizer getOrganizer(Integer organizerId) {
        return organizerRepository.findById(organizerId).orElseThrow(()->
                new RuntimeException("Organizer not found with id: " + organizerId));
    }

    @Override
    public void createOrganizer(OrganizerRequest organizerRequest) {
        Organizer organizer = new Organizer();
        organizer.setEmail(organizerRequest.getEmail());
        organizer.setDisplayName(organizerRequest.getDisplayName());
        organizer.setSelf(organizerRequest.isSelf());
        organizerRepository.save(organizer);
    }

    @Override
    public void updateOrganizer(Integer organizerId, OrganizerRequest organizerRequest) {
        Organizer organizer =organizerRepository.findById(organizerId).orElseThrow(()->
                new RuntimeException("Organizer not found with id: " + organizerId));
        organizer.setEmail(organizerRequest.getEmail());
        organizer.setDisplayName(organizerRequest.getDisplayName());
        organizer.setSelf(organizerRequest.isSelf());
        organizerRepository.save(organizer);
    }

    @Override
    public void deleteOrganizer(Integer organizerId) {
        Organizer organizer =organizerRepository.findById(organizerId).orElseThrow(()->
                new RuntimeException("Organizer not found with id: " + organizerId));
        organizerRepository.delete(organizer);
    }
}
