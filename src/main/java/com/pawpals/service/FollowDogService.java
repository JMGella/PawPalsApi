package com.pawpals.service;

import com.pawpals.model.dto.DogOutDTO;
import com.pawpals.model.dto.FollowDogInDTO;
import com.pawpals.model.dto.FollowDogOutDTO;
import com.pawpals.model.Dog;
import com.pawpals.model.FollowDog;
import com.pawpals.model.User;
import com.pawpals.repository.DogRepository;
import com.pawpals.repository.FollowDogRepository;
import com.pawpals.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.List;

@Service
@Transactional
public class FollowDogService {

    private final FollowDogRepository followDogRepository;
    private final UserRepository userRepository;
    private final DogRepository dogRepository;
    private final ModelMapper modelMapper;

    public FollowDogService(FollowDogRepository followDogRepository, UserRepository userRepository, DogRepository dogRepository, ModelMapper modelMapper) {
        this.followDogRepository = followDogRepository;
        this.userRepository = userRepository;
        this.dogRepository = dogRepository;
        this.modelMapper = modelMapper;
    }

    public FollowDogOutDTO followDog(FollowDogInDTO in) {
        User follower = userRepository.findById(in.getFollowerUserId()).orElseThrow(() -> new ResourceNotFoundException("Follower user not found"));
        Dog dog = dogRepository.findById(in.getDogId()).orElseThrow(() -> new ResourceNotFoundException("Dog not found"));

        if (followDogRepository.existsByFollowerIdAndDogId(follower.getId(), dog.getId())) {
            throw new IllegalStateException("Already following this dog");
        }

        FollowDog follow = new FollowDog();
        follow.setFollower(follower);
        follow.setDog(dog);

        FollowDog saved = followDogRepository.save(follow);

        FollowDogOutDTO out = new FollowDogOutDTO();
        out.setId(saved.getId());
        out.setFollowerUserId(follower.getId());
        out.setDogId(dog.getId());
        out.setCreatedAt(saved.getCreatedAt());
        return out;
    }

    public void unfollowDog(Long followerId, Long dogId) {
        if (!followDogRepository.existsByFollowerIdAndDogId(followerId, dogId)) {
            return;
        }
        followDogRepository.deleteByFollowerIdAndDogId(followerId, dogId);
    }

    @Transactional(readOnly = true)
    public List<DogOutDTO> getFollowedDogs(Long followerId) {
        List<FollowDog> follows = followDogRepository.findByFollowerId(followerId);
        List<Dog> dogs = follows.stream().map(FollowDog::getDog).toList();

        List<DogOutDTO> dogOutDTOList = modelMapper.map(dogs, new TypeToken<List<DogOutDTO>>() {}.getType());
        return dogOutDTOList;
    }
}
