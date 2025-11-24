package com.pawpals.repository;

import com.pawpals.model.ParticipationStatus;
import com.pawpals.model.WalkDog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WalkDogRepository extends JpaRepository<WalkDog, Long> {

    boolean existsByWalkIdAndDogId(Long walkId, Long dogId);

    List<WalkDog> findByWalkId(Long walkId);

    List<WalkDog> findByDogId(Long dogId);

    long countByWalkIdAndStatus(Long walkId, ParticipationStatus status);

    @Query("SELECT wd FROM WalkDog wd WHERE wd.dog.owner.id = :userId")
    List<WalkDog> findByUserDogs(@Param("userId") Long userId);


}
