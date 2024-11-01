package com.furidaweb.server.controller;

import com.furidaweb.server.dto.AuthResponse;
import com.furidaweb.server.dto.SignInUserDto;
import com.furidaweb.server.dto.SignUpUserDto;
import com.furidaweb.server.entity.User;
import com.furidaweb.server.service.AuthService;
import com.furidaweb.server.service.JwtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
@Validated
public class AuthController {

    private final JwtService jwtService;
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpUserDto signUpUserDto) {
        try {
            authService.signUp(signUpUserDto);
            AuthResponse authResponse = AuthResponse.builder()
                    .message("User registered.")
                    .build();

            return ResponseEntity.ok(authResponse);
        } catch (IllegalArgumentException e) {
            // Handle the case where the username or email already exists
            AuthResponse authResponse = AuthResponse.builder()
                    .message(e.getMessage())
                    .build();

            return ResponseEntity.badRequest().body(authResponse);
        } catch (Exception e) {
            AuthResponse authResponse = AuthResponse.builder()
                    .message(e.getMessage())
                    .build();

            return ResponseEntity.internalServerError().body(authResponse);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody SignInUserDto signInUserDto) {
        try {
            User authenticatedUser = authService.signIn(signInUserDto);
            String jwtToken = jwtService.generateToken(authenticatedUser);

            AuthResponse authResponse = AuthResponse.builder()
                    .token(jwtToken)
                    .build();

            return ResponseEntity.ok(authResponse);
        } catch (Exception e) {
            AuthResponse authResponse = AuthResponse.builder()
                    .message(e.getMessage())
                    .build();

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(authResponse);
        }
    }
}
