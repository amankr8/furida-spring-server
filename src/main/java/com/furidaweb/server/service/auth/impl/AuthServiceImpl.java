package com.furidaweb.server.service.auth.impl;

import com.furidaweb.server.dto.user.SignInUserDto;
import com.furidaweb.server.dto.user.SignUpUserDto;
import com.furidaweb.server.entity.User;
import com.furidaweb.server.repository.UserRepository;
import com.furidaweb.server.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final AuthenticationManager authenticationManager;

    @Override
    public User signUp(SignUpUserDto input) {
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
        user.setRole(input.getRole());

        return userRepository.save(user);
    }

    @Override
    public User signIn(SignInUserDto input) {
        User user = userRepository.findByUsername(input.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    input.getUsername(),
                    input.getPassword()
            )
        );
        return user;
    }
}
