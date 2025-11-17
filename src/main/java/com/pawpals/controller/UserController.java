package com.pawpals.controller;

import com.pawpals.model.dto.UserInDTO;
import com.pawpals.model.dto.UserOutDTO;
import com.pawpals.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pawpalsapi/users")
@Tag(name = "Users", description = "User management endpoints")
public class UserController {

    private final UserService userService;
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Create new user")
    @PostMapping
    public ResponseEntity<UserOutDTO> createUser(@Valid @RequestBody UserInDTO body) {
        logger.info("BEGIN Create User");
        UserOutDTO created = userService.createUser(body);
        logger.info("END Create User");
        return ResponseEntity.ok(created);
    }

    @Operation(summary = "Get user by id")
    @GetMapping("/{userId}")
    public ResponseEntity<UserOutDTO> getUser(@PathVariable Long userId) {
        logger.info(" Geting User");
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @Operation(summary = "Get all users")
    @GetMapping
    public ResponseEntity<List<UserOutDTO>> getAllUsers() {
        logger.info("GET All Users");
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @Operation(summary = "Delete user by id")
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        logger.info("BEGIN DELETE User");
        userService.deleteUser(userId);
        logger.info("END DELETE User");
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Update user by id")
    @PatchMapping("/{userId}")
    public ResponseEntity<UserOutDTO> updateUser(@PathVariable Long userId, @RequestBody UserInDTO body) {
        logger.info("BEGIN UPDATE User");
        UserOutDTO updated = userService.updateUserPartial(userId, body);
        logger.info("END UPDATE User");
        return ResponseEntity.ok(updated);
    }


}

