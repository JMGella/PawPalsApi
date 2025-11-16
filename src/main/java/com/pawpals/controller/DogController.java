package com.pawpals.controller;

import com.pawpals.model.dto.DogInDTO;
import com.pawpals.model.dto.DogOutDTO;
import com.pawpals.security.SecurityConfig;
import com.pawpals.service.DogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pawpalsapi")
@Tag(name = "Dogs", description = "Dog management endpoints")
public class DogController {

    private final DogService dogService;
    private static final Logger logger = LoggerFactory.getLogger(DogController.class);

    public DogController(DogService dogService) {
        this.dogService = dogService;
    }

    @Operation(summary = "Create dog for a user")
    @PostMapping("/users/{userId}/dogs")
    public ResponseEntity<DogOutDTO> createDog(@PathVariable Long userId, @Valid @RequestBody DogInDTO body) {
        body.setOwnerId(userId); // forzamos el owner desde la URL
        logger.info("BEGIN Creating dog {}", body);
        DogOutDTO created = dogService.createDog(body);
        logger.info("END Creating dog {}", created);
        return ResponseEntity.ok(created);
    }

    @Operation(summary = "Get all dogs for a user")
    @GetMapping("/users/{userId}/dogs")
    public ResponseEntity<List<DogOutDTO>> getDogsByUser(@PathVariable Long userId) {
        logger.info(" Getting dogs for a user {}", userId);
        return ResponseEntity.ok(dogService.getDogsByOwner(userId));
    }

    @Operation(summary = "Get dog by id")
    @GetMapping("/dogs/{dogId}")
    public ResponseEntity<DogOutDTO> getDog(@PathVariable Long dogId) {
        logger.info(" Getting dog {}", dogId);
        return ResponseEntity.ok(dogService.getDogById(dogId));
    }

    @Operation(summary = "Delete dog by id")
    @DeleteMapping("/dogs/{dogId}")
    public ResponseEntity<Void> deleteDog(@PathVariable Long dogId) {
        logger.info(" BEGIN Deleting dog {}", dogId);
        dogService.deleteDog(dogId);
        logger.info("END Deleting dog {}", dogId);
        return ResponseEntity.noContent().build();
    }

}
