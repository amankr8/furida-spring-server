package com.furidaweb.server.controller;

import com.furidaweb.server.dto.AuthResponse;
import com.furidaweb.server.dto.SignInUserDto;
import com.furidaweb.server.dto.SignUpUserDto;
import com.furidaweb.server.entity.User;
import com.furidaweb.server.service.AuthService;
import com.furidaweb.server.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthController {

    private final JwtService jwtService;
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> register(@RequestBody SignUpUserDto signUpUserDto) {
        User registeredUser = authService.signUp(signUpUserDto);
        String jwtToken = jwtService.generateToken(registeredUser);

        AuthResponse authResponse = AuthResponse.builder()
                .token(jwtToken)
                .build();

        return ResponseEntity.ok(authResponse);
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody SignInUserDto signInUserDto) {
        User authenticatedUser = authService.signIn(signInUserDto);
        String jwtToken = jwtService.generateToken(authenticatedUser);

        AuthResponse authResponse = AuthResponse.builder()
                .token(jwtToken)
                .build();

        return ResponseEntity.ok(authResponse);
    }
}
