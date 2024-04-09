package com.example.Task.Management.Helpers.OrganizerHelper;

import com.example.Task.Management.Entity.Organizer;

import java.util.List;

public class OrganizerResponse {
    List<Organizer> organizers;

    public OrganizerResponse() {
    }

    public OrganizerResponse(List<Organizer> organizers) {
        this.organizers = organizers;
    }

    public List<Organizer> getOrganizers() {
        return organizers;
    }

    public void setOrganizers(List<Organizer> organizers) {
        this.organizers = organizers;
    }
}
