package com.pawpals.controller;

import com.pawpals.model.dto.DogOutDTO;
import com.pawpals.model.dto.FollowDogInDTO;
import com.pawpals.model.dto.FollowDogOutDTO;
import com.pawpals.service.FollowDogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pawpalsapi/users/{userId}/follows")
@Tag(name = "Dog Follows", description = "Follow/unfollow dogs")
public class FollowDogController {

    private final FollowDogService followDogService;

    public FollowDogController(FollowDogService followDogService) {
        this.followDogService = followDogService;
    }

    @Operation(summary = "Get dogs followed by a user")
    @GetMapping("/dogs")
    public ResponseEntity<List<DogOutDTO>> getFollowedDogs(@PathVariable Long userId) {
        return ResponseEntity.ok(followDogService.getFollowedDogs(userId));
    }

    @Operation(summary = "Follow a dog")
    @PostMapping("/dogs/{dogId}")
    public ResponseEntity<FollowDogOutDTO> followDog(@PathVariable Long userId, @PathVariable Long dogId) {
        FollowDogInDTO in = new FollowDogInDTO(userId, dogId);
        FollowDogOutDTO out = followDogService.followDog(in);
        return ResponseEntity.ok(out);
    }

    @Operation(summary = "Unfollow a dog")
    @DeleteMapping("/dogs/{dogId}")
    public ResponseEntity<Void> unfollowDog(@PathVariable Long userId, @PathVariable Long dogId) {
        followDogService.unfollowDog(userId, dogId);
        return ResponseEntity.noContent().build();
    }
}
