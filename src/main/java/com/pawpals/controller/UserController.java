package com.pawpals.controller;

import com.pawpals.model.dto.UserInDTO;
import com.pawpals.model.dto.UserOutDTO;
import com.pawpals.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pawpalsapi/users")
@Tag(name = "Users", description = "User management endpoints")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Create new user")
    @PostMapping
    public ResponseEntity<UserOutDTO> createUser(@Valid @RequestBody UserInDTO body) {
        UserOutDTO created = userService.createUser(body);
        return ResponseEntity.ok(created);
    }

    @Operation(summary = "Get user by id")
    @GetMapping("/{userId}")
    public ResponseEntity<UserOutDTO> getUser(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @Operation(summary = "Get all users")
    @GetMapping
    public ResponseEntity<List<UserOutDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
}

