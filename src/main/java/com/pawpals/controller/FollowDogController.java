package com.pawpals.controller;

import com.pawpals.model.dto.DogOutDTO;
import com.pawpals.model.dto.FollowDogInDTO;
import com.pawpals.model.dto.FollowDogOutDTO;
import com.pawpals.service.FollowDogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pawpalsapi/users/{userId}")
@Tag(name = "Dog Follows", description = "Follow/unfollow dogs")
public class FollowDogController {

    private final FollowDogService followDogService;
    private final Logger logger = LoggerFactory.getLogger(FollowDogController.class);

    public FollowDogController(FollowDogService followDogService) {
        this.followDogService = followDogService;
    }

    @Operation(summary = "Get dogs followed by a user")
    @GetMapping("/followed")
    public ResponseEntity<List<DogOutDTO>> getFollowedDogs(@PathVariable Long userId) {
        logger.info("getFollowedDogs called");
        return ResponseEntity.ok(followDogService.getFollowedDogs(userId));
    }

    @Operation(summary = "Follow a dog")
    @PostMapping("/follow-dog/{dogId}")
    public ResponseEntity<FollowDogOutDTO> followDog(@PathVariable Long userId, @PathVariable Long dogId) {
        FollowDogInDTO in = new FollowDogInDTO(userId, dogId);
        logger.info("BEGIN followDog");
        FollowDogOutDTO out = followDogService.followDog(in);
        logger.info("END followDog");
        return ResponseEntity.ok(out);
    }

    @Operation(summary = "Unfollow a dog")
    @DeleteMapping("/follow-dog/{dogId}")
    public ResponseEntity<Void> unfollowDog(@PathVariable Long userId, @PathVariable Long dogId) {
        logger.info("BEGIN unfollowDog");
        followDogService.unfollowDog(userId, dogId);
        logger.info("END unfollowDog");
        return ResponseEntity.noContent().build();
    }
}
