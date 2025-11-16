package com.pawpals.controller;

import com.pawpals.model.dto.DogInDTO;
import com.pawpals.model.dto.DogOutDTO;
import com.pawpals.service.DogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pawpalsapi")
@Tag(name = "Dogs", description = "Dog management endpoints")
public class DogController {

    private final DogService dogService;

    public DogController(DogService dogService) {
        this.dogService = dogService;
    }

    @Operation(summary = "Create dog for a user")
    @PostMapping("/users/{userId}/dogs")
    public ResponseEntity<DogOutDTO> createDog(@PathVariable Long userId, @Valid @RequestBody DogInDTO body) {
        body.setOwnerId(userId); // forzamos el owner desde la URL
        DogOutDTO created = dogService.createDog(body);
        return ResponseEntity.ok(created);
    }

    @Operation(summary = "Get all dogs for a user")
    @GetMapping("/users/{userId}/dogs")
    public ResponseEntity<List<DogOutDTO>> getDogsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(dogService.getDogsByOwner(userId));
    }

    @Operation(summary = "Get dog by id")
    @GetMapping("/dogs/{dogId}")
    public ResponseEntity<DogOutDTO> getDog(@PathVariable Long dogId) {
        return ResponseEntity.ok(dogService.getDogById(dogId));
    }

    @Operation(summary = "Delete dog by id")
    @DeleteMapping("/dogs/{dogId}")
    public ResponseEntity<Void> deleteDog(@PathVariable Long dogId) {
        dogService.deleteDog(dogId);
        return ResponseEntity.noContent().build();
    }

}
