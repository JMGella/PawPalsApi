package com.pawpals.service;

import com.pawpals.model.dto.WalkDogInDTO;
import com.pawpals.model.dto.WalkDogOutDTO;
import com.pawpals.model.*;
import com.pawpals.repository.DogRepository;
import com.pawpals.repository.UserRepository;
import com.pawpals.repository.WalkDogRepository;
import com.pawpals.repository.WalkRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.List;

@Service
@Transactional
public class WalkDogService {

    private final WalkDogRepository walkDogRepository;
    private final WalkRepository walkRepository;
    private final DogRepository dogRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public WalkDogService(WalkDogRepository walkDogRepository, WalkRepository walkRepository, DogRepository dogRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.walkDogRepository = walkDogRepository;
        this.walkRepository = walkRepository;
        this.dogRepository = dogRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public WalkDogOutDTO joinWalk(WalkDogInDTO in) {
        Walk walk = walkRepository.findById(in.getWalkId()).orElseThrow(() -> new IllegalArgumentException("Walk not found"));
        Dog dog = dogRepository.findById(in.getDogId()).orElseThrow(() -> new IllegalArgumentException("Dog not found"));
        User handler = userRepository.findById(in.getHandlerId()).orElseThrow(() -> new IllegalArgumentException("Handler not found"));

        if (walkDogRepository.existsByWalkIdAndDogId(walk.getId(), dog.getId())) {
            throw new IllegalStateException("Dog already joined this walk");
        }

        if (walk.getMaxDogs() != null) {
            long joinedCount = walkDogRepository.countByWalkIdAndStatus(walk.getId(), ParticipationStatus.JOINED);
            if (joinedCount >= walk.getMaxDogs()) {
                throw new IllegalStateException("Walk is full");
            }
        }

        WalkDog walkDog = new WalkDog();
        walkDog.setWalk(walk);
        walkDog.setDog(dog);
        walkDog.setHandler(handler);
        walkDog.setStatus(ParticipationStatus.JOINED);

        WalkDog saved = walkDogRepository.save(walkDog);
        return modelMapper.map(saved, WalkDogOutDTO.class);
    }

    @Transactional(readOnly = true)
    public List<WalkDogOutDTO> getParticipantsByWalk(Long walkId) {
        List<WalkDog> list = walkDogRepository.findByWalkId(walkId);
        List<WalkDogOutDTO> walkDogOutDTOList = modelMapper.map(list, new TypeToken<List<WalkDogOutDTO>>() {}.getType());
        return walkDogOutDTOList;
    }

    public void leaveWalk(Long walkDogId) {
        WalkDog wd = walkDogRepository.findById(walkDogId).orElseThrow(() -> new IllegalArgumentException("Participation not found"));
        walkDogRepository.delete(wd);
    }

    @Transactional
    public WalkDogOutDTO updateParticipationStatus(Long walkDogId, WalkDogInDTO body) {

        WalkDog walkDog = walkDogRepository.findById(walkDogId).orElseThrow(() -> new IllegalArgumentException("Participation not found"));

        if (body.getStatus() != null) {
            walkDog.setStatus(body.getStatus());
            walkDog = walkDogRepository.save(walkDog);
        }

        return modelMapper.map(walkDog, WalkDogOutDTO.class);
    }

    @Transactional(readOnly = true)
    public List<WalkDogOutDTO> getWalkParticipationByDog(Long dogId) {
    List<WalkDog> participations = walkDogRepository.findByDogId(dogId);

    return modelMapper.map(participations, new TypeToken<List<WalkDogOutDTO>>() {}.getType());
}



}
