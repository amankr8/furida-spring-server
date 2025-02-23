package com.furidaweb.server.service.auth.impl;

import com.furidaweb.server.dto.auth.UpdatePassDto;
import com.furidaweb.server.dto.auth.SignInUserDto;
import com.furidaweb.server.dto.auth.SignUpUserDto;
import com.furidaweb.server.dto.user.UserResponseDto;
import com.furidaweb.server.entity.User;
import com.furidaweb.server.repository.UserRepository;
import com.furidaweb.server.service.auth.AuthService;
import com.furidaweb.server.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public UserResponseDto signUp(SignUpUserDto input) {
        // Check if the user already exists by username or email
        if (userRepository.findByUsername(input.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username already exists");
        }

        if (userRepository.findByEmail(input.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }

        User user = new User();
        user.setEmail(input.getEmail());
        user.setUsername(input.getUsername());
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        user.setCreateDate(new Date());
        user.setRole(input.getRole());

        return userService.createUserResponseDto(userRepository.save(user));
    }

    @Override
    public UserResponseDto signIn(SignInUserDto input) {
        User user = userRepository.findByUsername(input.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    input.getUsername(),
                    input.getPassword()
            )
        );
        return userService.createUserResponseDto(user);
    }

    @Override
    public void updatePass(String username, UpdatePassDto updatePassDto) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // Check if the old password matches
        if (!passwordEncoder.matches(updatePassDto.getOldPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Password is incorrect");
        }

        // Update to the new password
        user.setPassword(passwordEncoder.encode(updatePassDto.getNewPassword()));
        userRepository.save(user);
    }
}
