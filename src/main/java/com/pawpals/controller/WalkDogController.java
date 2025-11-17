package com.pawpals.controller;

import com.pawpals.model.dto.WalkDogInDTO;
import com.pawpals.model.dto.WalkDogOutDTO;
import com.pawpals.service.WalkDogService;
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
@Tag(name = "Walk Participation", description = "Dog participation in walks")
public class WalkDogController {

    private final WalkDogService walkDogService;
    private final Logger logger = LoggerFactory.getLogger(WalkDogController.class);

    public WalkDogController(WalkDogService walkDogService) {
        this.walkDogService = walkDogService;
    }

    @Operation(summary = "Join a walk with a dog")
    @PostMapping("/walks/{walkId}/dogs")
    public ResponseEntity<WalkDogOutDTO> joinWalk(@PathVariable Long walkId, @Valid @RequestBody WalkDogInDTO body) {
        body.setWalkId(walkId);
        logger.info("BEGIN Joining walk " + body.getWalkId());
        WalkDogOutDTO out = walkDogService.joinWalk(body);
        logger.info("END Joining walk " + body.getWalkId());
        return ResponseEntity.ok(out);
    }

    @Operation(summary = "Get all dogs participating in a walk")
    @GetMapping("/walks/{walkId}/dogs")
    public ResponseEntity<List<WalkDogOutDTO>> getParticipants(@PathVariable Long walkId) {
        logger.info(" Getting all dogs participating in a walk");
        return ResponseEntity.ok(walkDogService.getParticipantsByWalk(walkId));
    }

    @Operation(summary = "Remove a dog participation from a walk")
    @DeleteMapping("/walks/dogs/{walkDogId}")
    public ResponseEntity<Void> leaveWalk(@PathVariable Long walkDogId) {
        logger.info("BEGIN Leaving walk " + walkDogId);
        walkDogService.leaveWalk(walkDogId);
        logger.info("END Leaving walk " + walkDogId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Update dog participation status in a walk")
    @PatchMapping("/walks/dogs/{walkDogId}")
    public ResponseEntity<WalkDogOutDTO> updateParticipationStatus(@PathVariable Long walkDogId, @RequestBody WalkDogInDTO body) {

        logger.info("BEGIN Update participation status " + walkDogId);
        WalkDogOutDTO updated = walkDogService.updateParticipationStatus(walkDogId, body);
        logger.info("END Update participation status " + walkDogId);

        return ResponseEntity.ok(updated);
    }

}
