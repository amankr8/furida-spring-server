package com.furidaweb.server.controller;

import com.furidaweb.server.dto.AuthResponse;
import com.furidaweb.server.dto.SignInUserDto;
import com.furidaweb.server.dto.SignUpUserDto;
import com.furidaweb.server.entity.User;
import com.furidaweb.server.service.AuthService;
import com.furidaweb.server.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthController {

    private final JwtService jwtService;
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody SignUpUserDto signUpUserDto) {
        try {
            authService.signUp(signUpUserDto);
            return ResponseEntity.ok("User registered successfully!");
        } catch (IllegalArgumentException e) {
            // Handle the case where the username or email already exists
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticate(@RequestBody SignInUserDto signInUserDto) {
        try {
            User authenticatedUser = authService.signIn(signInUserDto);
            String jwtToken = jwtService.generateToken(authenticatedUser);

            AuthResponse authResponse = AuthResponse.builder()
                    .token(jwtToken)
                    .build();

            return ResponseEntity.ok(authResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
