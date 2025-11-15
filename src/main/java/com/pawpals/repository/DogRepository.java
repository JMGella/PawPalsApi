package com.pawpals.repository;

import com.pawpals.model.Dog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DogRepository extends JpaRepository<Dog, Long> {

    List<Dog> findByOwnerId(Long ownerId);

}