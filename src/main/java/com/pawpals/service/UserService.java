package com.pawpals.service;

import com.pawpals.model.User;
import com.pawpals.model.dto.UserInDTO;
import com.pawpals.model.dto.UserOutDTO;
import com.pawpals.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public UserOutDTO createUser(UserInDTO in) {
        if (userRepository.existsByEmail(in.getEmail())) {
            throw new IllegalArgumentException("Email already in use");
        }
        if (in.getUsername() != null && userRepository.existsByUsername(in.getUsername())) {
            throw new IllegalArgumentException("Username already in use");
        }

        User user = new User();
        user.setEmail(in.getEmail());
        user.setPasswordHash(passwordEncoder.encode(in.getPassword()));
        user.setDisplayName(in.getDisplayName());
        user.setUsername(in.getUsername());
        user.setProfileImageUrl(in.getProfileImageUrl());

        User saved = userRepository.save(user);
        return modelMapper.map(saved, UserOutDTO.class);
    }

    @Transactional(readOnly = true)
    public UserOutDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
        return modelMapper.map(user, UserOutDTO.class);
    }

    @Transactional(readOnly = true)
    public List<UserOutDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserOutDTO> userOutDTOList = modelMapper.map(users, new TypeToken<List<UserOutDTO>>() {}.getType());
        return userOutDTOList;
    }

    @Transactional
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("User not found");
        }
        userRepository.deleteById(id);
    }

    @Transactional
    public UserOutDTO updateUserPartial(Long userId, UserInDTO body) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (body.getDisplayName() != null) user.setDisplayName(body.getDisplayName());
        if (body.getUsername() != null) user.setUsername(body.getUsername());
        if (body.getProfileImageUrl() != null) user.setProfileImageUrl(body.getProfileImageUrl());

        User saved = userRepository.save(user);
        return modelMapper.map(saved, UserOutDTO.class);
    }

}