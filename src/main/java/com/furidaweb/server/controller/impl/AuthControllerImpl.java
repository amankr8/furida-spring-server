package com.furidaweb.server.controller.impl;

import com.furidaweb.server.controller.AuthController;
import com.furidaweb.server.dto.auth.AuthResponse;
import com.furidaweb.server.dto.auth.UpdatePassDto;
import com.furidaweb.server.dto.auth.SignInUserDto;
import com.furidaweb.server.dto.auth.SignUpUserDto;
import com.furidaweb.server.entity.User;
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
        User registeredUser = authService.signUp(signUpUserDto);
        String jwtToken = jwtService.generateToken(registeredUser);
        return ResponseEntity.ok(new AuthResponse(jwtToken, "User registered successfully!"));
    }

    @Override
    public ResponseEntity<?> authenticateUser(SignInUserDto signInUserDto) {
        User authenticatedUser = authService.signIn(signInUserDto);
        String jwtToken = jwtService.generateToken(authenticatedUser);
        return ResponseEntity.ok(new AuthResponse(jwtToken, "Login successful!"));
    }

    @Override
    public ResponseEntity<?> updatePassword(UpdatePassDto updatePassDto, Principal principal) {
        authService.updatePass(principal.getName(), updatePassDto);
        return ResponseEntity.ok(new AuthResponse(null, "Password updated successfully!"));
    }
}
