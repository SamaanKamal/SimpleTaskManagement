package com.example.Task.Management.Service.DetailedServices.OrganizerService;



import com.example.Task.Management.Entity.Organizer;

import java.util.List;

public interface IOrganizerService {
    List<Organizer> getAllOrganizers();
    Organizer getOrganizer(Integer organizerId);
    void createOrganizer();
    void updateOrganizer(Integer organizerId);
    void deleteOrganizer(Integer organizerId);
}
