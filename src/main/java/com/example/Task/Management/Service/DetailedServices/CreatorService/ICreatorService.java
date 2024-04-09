package com.example.Task.Management.Service.DetailedServices.CreatorService;



import com.example.Task.Management.Entity.Creator;

import java.util.List;

public interface ICreatorService {
    List<Creator> getAllCreators();
    Creator getCreator(Integer creatorId);
    void createCreator();
    void updateCreator(Integer creatorId);
    void deleteCreator(Integer creatorId);
}
