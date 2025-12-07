package com.pawpals.repository;

import com.pawpals.model.FollowDog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FollowDogRepository extends JpaRepository<FollowDog, Long> {

    boolean existsByFollowerIdAndDogId(Long followerId, Long dogId);

    List<FollowDog> findByFollowerId(Long followerId);

    void deleteByFollowerIdAndDogId(Long followerId, Long dogId);

}