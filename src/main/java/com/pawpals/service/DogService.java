package com.pawpals.service;

import com.pawpals.model.dto.DogInDTO;
import com.pawpals.model.dto.DogOutDTO;
import com.pawpals.model.Dog;
import com.pawpals.model.User;
import com.pawpals.repository.DogRepository;
import com.pawpals.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.List;

@Service
@Transactional
public class DogService {

    private final DogRepository dogRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public DogService(DogRepository dogRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.dogRepository = dogRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public DogOutDTO createDog(Long userId, DogInDTO in) {
        User owner = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Owner not found"));

        Dog dog = new Dog();
        dog.setOwner(owner);
        dog.setName(in.getName());
        dog.setBreed(in.getBreed());
        dog.setBirthdate(in.getBirthdate());
        dog.setDescription(in.getDescription());
        dog.setProfileImageUrl(in.getProfileImageUrl());

        Dog saved = dogRepository.save(dog);
        return modelMapper.map(saved, DogOutDTO.class);
    }

    @Transactional(readOnly = true)
    public DogOutDTO getDogById(Long id) {
        Dog dog = dogRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Dog not found"));
        return modelMapper.map(dog, DogOutDTO.class);
    }

    @Transactional(readOnly = true)
    public List<DogOutDTO> getDogsByOwner(Long ownerId) {
        List<Dog> dogs = dogRepository.findByOwnerId(ownerId);
        List<DogOutDTO> dogOutDTOList = modelMapper.map(dogs, new TypeToken<List<DogOutDTO>>() {}.getType());
        return dogOutDTOList;
    }

    @Transactional
    public void deleteDog(Long id) {
        if (!dogRepository.existsById(id)) {
            throw new IllegalArgumentException("Dog not found");
        }
        dogRepository.deleteById(id);
    }

    @Transactional
    public DogOutDTO updateDog(Long dogId, DogInDTO body) {

        Dog dog = dogRepository.findById(dogId).orElseThrow(() -> new IllegalArgumentException("Dog not found"));


        if (body.getName() != null) {
            dog.setName(body.getName());
        }
        if (body.getBreed() != null) {
            dog.setBreed(body.getBreed());
        }
        if (body.getBirthdate() != null) {
            dog.setBirthdate(body.getBirthdate());
        }
        if (body.getDescription() != null) {
            dog.setDescription(body.getDescription());
        }
        if (body.getProfileImageUrl() != null) {
            dog.setProfileImageUrl(body.getProfileImageUrl());
        }

        Dog saved = dogRepository.save(dog);
        return modelMapper.map(saved, DogOutDTO.class);
    }

    @Transactional(readOnly = true)
    public List<DogOutDTO> searchDogsByName(String name) {
        List<Dog> dogs = dogRepository.findByNameContainingIgnoreCase(name);
        return modelMapper.map(dogs, new TypeToken<List<DogOutDTO>>() {}.getType());
    }



}
