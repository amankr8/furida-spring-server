package com.furidaweb.server.controller.impl;

import com.furidaweb.server.controller.AuthController;
import com.furidaweb.server.dto.auth.AuthResponse;
import com.furidaweb.server.dto.auth.UpdatePassDto;
import com.furidaweb.server.dto.auth.SignInUserDto;
import com.furidaweb.server.dto.auth.SignUpUserDto;
import com.furidaweb.server.dto.user.UserResponseDto;
import com.furidaweb.server.service.auth.AuthService;
import com.furidaweb.server.service.auth.JwtService;
import com.furidaweb.server.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RequiredArgsConstructor
@RestController
public class AuthControllerImpl implements AuthController {

    @Autowired
    private final JwtService jwtService;
    @Autowired
    private final AuthService authService;
    @Autowired
    private final UserService userService;

    @Override
    public ResponseEntity<?> getAuthUser(Principal principal) {
        if (principal == null) {
            return ResponseEntity.ok(null);
        }
        return ResponseEntity.ok(userService.getUserByUsername(principal.getName()));
    }

    @Override
    public ResponseEntity<?> registerUser(SignUpUserDto signUpUserDto) {
        UserResponseDto registeredUser = authService.signUp(signUpUserDto);
        String jwtToken = jwtService.generateToken(registeredUser.getUsername());
        return ResponseEntity.ok(new AuthResponse(jwtToken, registeredUser, "User registered successfully!"));
    }

    @Override
    public ResponseEntity<?> authenticateUser(SignInUserDto signInUserDto) {
        UserResponseDto authenticatedUser = authService.signIn(signInUserDto);
        String jwtToken = jwtService.generateToken(authenticatedUser.getUsername());
        return ResponseEntity.ok(new AuthResponse(jwtToken, authenticatedUser, "Login successful!"));
    }

    @Override
    public ResponseEntity<?> updatePassword(UpdatePassDto updatePassDto, Principal principal) {
        authService.updatePass(principal.getName(), updatePassDto);
        return ResponseEntity.ok(new AuthResponse(null, null, "Password updated successfully!"));
    }
}
