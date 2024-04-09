package com.example.Task.Management.Service.DetailedServices.CreatorService;

import com.example.Task.Management.Entity.Creator;
import com.example.Task.Management.Helpers.CreatorHelper.CreatorRequest;
import com.example.Task.Management.Repository.CreatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreatorService implements  ICreatorService {

    @Autowired
    private CreatorRepository creatorRepository;

    @Override
    public List<Creator> getAllCreators() {
        return creatorRepository.findAll();
    }

    @Override
    public Creator getCreator(Integer creatorId) {
        return creatorRepository.findById(creatorId).orElseThrow(()->
                new RuntimeException("Creator not found with id: " + creatorId));
    }

    @Override
    public void createCreator(CreatorRequest creatorRequest) {
        Creator creator = new Creator();
        creator.setEmail(creatorRequest.getEmail());
        creator.setDisplayName(creatorRequest.getDisplayName());
        creator.setSelf(creatorRequest.isSelf());
        creatorRepository.save(creator);
    }

    @Override
    public Creator updateCreator(Integer creatorId, CreatorRequest creatorRequest) {
        Creator creator =creatorRepository.findById(creatorId).orElseThrow(()->
                new RuntimeException("Creator not found with id: " + creatorId));
        creator.setEmail(creatorRequest.getEmail());
        creator.setDisplayName(creatorRequest.getDisplayName());
        creator.setSelf(creatorRequest.isSelf());
        Creator updatedCreator =creatorRepository.save(creator);
        return updatedCreator;
    }

    @Override
    public void deleteCreator(Integer creatorId) {
        Creator creator =creatorRepository.findById(creatorId).orElseThrow(()->
                new RuntimeException("Creator not found with id: " + creatorId));
        creatorRepository.delete(creator);
    }
}
