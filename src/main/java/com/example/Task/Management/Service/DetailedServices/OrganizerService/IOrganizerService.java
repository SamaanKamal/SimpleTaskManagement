package com.example.Task.Management.Service.DetailedServices.OrganizerService;



import com.example.Task.Management.Entity.Organizer;
import com.example.Task.Management.Helpers.OrganizerHelper.OrganizerRequest;

import java.util.List;

public interface IOrganizerService {
    List<Organizer> getAllOrganizers();
    Organizer getOrganizer(Integer organizerId);
    void createOrganizer(OrganizerRequest organizerRequest);
    Organizer updateOrganizer(Integer organizerId,OrganizerRequest organizerRequest);
    void deleteOrganizer(Integer organizerId);
}
