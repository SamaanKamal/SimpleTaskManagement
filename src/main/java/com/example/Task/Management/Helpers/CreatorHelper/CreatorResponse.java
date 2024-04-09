package com.example.Task.Management.Helpers.CreatorHelper;

import com.example.Task.Management.Entity.Creator;

import java.util.List;

public class CreatorResponse {
    List<Creator> creators;

    public CreatorResponse() {
    }

    public CreatorResponse(List<Creator> creators) {
        this.creators = creators;
    }

    public List<Creator> getCreators() {
        return creators;
    }

    public void setCreators(List<Creator> creators) {
        this.creators = creators;
    }
}
