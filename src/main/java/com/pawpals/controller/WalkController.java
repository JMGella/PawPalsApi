package com.pawpals.controller;

import com.pawpals.model.dto.WalkInDTO;
import com.pawpals.model.dto.WalkOutDTO;
import com.pawpals.service.WalkService;
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
@Tag(name = "Walks", description = "Walk management endpoints")
public class WalkController {

    private final WalkService walkService;
    private final Logger logger = LoggerFactory.getLogger(WalkController.class);

    public WalkController(WalkService walkService) {
        this.walkService = walkService;
    }

    @Operation(summary = "Create a walk for a user")
    @PostMapping("/users/{userId}/walks")
    public ResponseEntity<WalkOutDTO> createWalk(@PathVariable Long userId, @Valid @RequestBody WalkInDTO body) {
        body.setCreatorId(userId);
        logger.info("BEGIN Creating a walk for user " + userId);
        WalkOutDTO created = walkService.createWalk(body);
        logger.info("END Creating a walk for user " + userId);
        return ResponseEntity.ok(created);
    }

    @Operation(summary = "Get walks created by a user")
    @GetMapping("/users/{userId}/walks")
    public ResponseEntity<List<WalkOutDTO>> getWalksByCreator(@PathVariable Long userId) {
        logger.info(" Geting walks created by a user");
        return ResponseEntity.ok(walkService.getWalksByCreator(userId));
    }

    @Operation(summary = "Get walk by id")
    @GetMapping("/walks/{walkId}")
    public ResponseEntity<WalkOutDTO> getWalk(@PathVariable Long walkId) {
        logger.info(" Get walk by id " + walkId);
        return ResponseEntity.ok(walkService.getWalkById(walkId));
    }

    @Operation(summary = "Get upcoming walks")
    @GetMapping("/walks/upcoming")
    public ResponseEntity<List<WalkOutDTO>> getUpcomingWalks() {
        logger.info(" Get upcoming walks");
        return ResponseEntity.ok(walkService.getUpcomingWalks());
    }

    @Operation(summary = "Cancel a walk")
    @PostMapping("/walks/{walkId}/cancel")
    public ResponseEntity<Void> cancelWalk(@PathVariable Long walkId) {
        logger.info(" BEGIN Cancel walk " + walkId);
        walkService.cancelWalk(walkId);
        logger.info("END Cancel walk " + walkId);
        return ResponseEntity.noContent().build();
    }
}
