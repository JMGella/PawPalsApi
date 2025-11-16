package com.pawpals.controller;

import com.pawpals.model.dto.WalkInDTO;
import com.pawpals.model.dto.WalkOutDTO;
import com.pawpals.service.WalkService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pawpalsapi")
@Tag(name = "Walks", description = "Walk management endpoints")
public class WalkController {

    private final WalkService walkService;

    public WalkController(WalkService walkService) {
        this.walkService = walkService;
    }

    @Operation(summary = "Create a walk for a user")
    @PostMapping("/users/{userId}/walks")
    public ResponseEntity<WalkOutDTO> createWalk(@PathVariable Long userId, @Valid @RequestBody WalkInDTO body) {
        body.setCreatorId(userId);
        WalkOutDTO created = walkService.createWalk(body);
        return ResponseEntity.ok(created);
    }

    @Operation(summary = "Get walks created by a user")
    @GetMapping("/users/{userId}/walks")
    public ResponseEntity<List<WalkOutDTO>> getWalksByCreator(@PathVariable Long userId) {
        return ResponseEntity.ok(walkService.getWalksByCreator(userId));
    }

    @Operation(summary = "Get walk by id")
    @GetMapping("/walks/{walkId}")
    public ResponseEntity<WalkOutDTO> getWalk(@PathVariable Long walkId) {
        return ResponseEntity.ok(walkService.getWalkById(walkId));
    }

    @Operation(summary = "Get upcoming walks")
    @GetMapping("/walks/upcoming")
    public ResponseEntity<List<WalkOutDTO>> getUpcomingWalks() {
        return ResponseEntity.ok(walkService.getUpcomingWalks());
    }

    @Operation(summary = "Cancel a walk")
    @PostMapping("/walks/{walkId}/cancel")
    public ResponseEntity<Void> cancelWalk(@PathVariable Long walkId) {
        walkService.cancelWalk(walkId);
        return ResponseEntity.noContent().build();
    }
}
