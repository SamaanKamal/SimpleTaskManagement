package com.example.Task.Management.Service.DetailedServices.CreatorService;



import com.example.Task.Management.Entity.Creator;
import com.example.Task.Management.Helpers.CreatorHelper.CreatorRequest;

import java.util.List;

public interface ICreatorService {
    List<Creator> getAllCreators();
    Creator getCreator(Integer creatorId);
    void createCreator(CreatorRequest creatorRequest);
    void updateCreator(Integer creatorId,CreatorRequest creatorRequest);
    void deleteCreator(Integer creatorId);
}
