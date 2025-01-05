package com.furidaweb.server.service.user.impl;

import com.furidaweb.server.dto.user.UserResponseDto;
import com.furidaweb.server.entity.User;
import com.furidaweb.server.exception.ResourceNotFoundException;
import com.furidaweb.server.repository.UserRepository;
import com.furidaweb.server.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;

    @Override
    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::createUserResponseDto)
                .toList();
    }

    @Override
    public UserResponseDto getUserById(int id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return createUserResponseDto(user);
    }

    @Override
    public UserResponseDto getUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return createUserResponseDto(user);
    }

    @Override
    public User updateUserDetails(int id, User user) {
        return null;
    }

    @Override
    public void deleteUserById(String username, int id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (Objects.equals(user.getUsername(), username)) {
            throw new IllegalArgumentException("Logged in user cannot be deleted.");
        }

        userRepository.deleteById(id);
    }

    @Override
    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

    private UserResponseDto createUserResponseDto(User user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole())
                .date(user.getCreateDate())
                .build();
    }
}
