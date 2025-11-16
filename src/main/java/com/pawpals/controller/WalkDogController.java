package com.pawpals.controller;

import com.pawpals.model.dto.WalkDogInDTO;
import com.pawpals.model.dto.WalkDogOutDTO;
import com.pawpals.service.WalkDogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pawpalsapi")
@Tag(name = "Walk Participation", description = "Dog participation in walks")
public class WalkDogController {

    private final WalkDogService walkDogService;

    public WalkDogController(WalkDogService walkDogService) {
        this.walkDogService = walkDogService;
    }

    @Operation(summary = "Join a walk with a dog")
    @PostMapping("/walks/{walkId}/dogs")
    public ResponseEntity<WalkDogOutDTO> joinWalk(@PathVariable Long walkId, @Valid @RequestBody WalkDogInDTO body) {
        body.setWalkId(walkId);
        WalkDogOutDTO out = walkDogService.joinWalk(body);
        return ResponseEntity.ok(out);
    }

    @Operation(summary = "Get all dogs participating in a walk")
    @GetMapping("/walks/{walkId}/dogs")
    public ResponseEntity<List<WalkDogOutDTO>> getParticipants(@PathVariable Long walkId) {
        return ResponseEntity.ok(walkDogService.getParticipantsByWalk(walkId));
    }

    @Operation(summary = "Remove a dog participation from a walk")
    @DeleteMapping("/walks/dogs/{walkDogId}")
    public ResponseEntity<Void> leaveWalk(@PathVariable Long walkDogId) {
        walkDogService.leaveWalk(walkDogId);
        return ResponseEntity.noContent().build();
    }
}
