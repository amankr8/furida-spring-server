package com.furidaweb.server.controller.impl;

import com.furidaweb.server.controller.AuthController;
import com.furidaweb.server.dto.auth.AuthResponse;
import com.furidaweb.server.dto.user.SignInUserDto;
import com.furidaweb.server.dto.user.SignUpUserDto;
import com.furidaweb.server.entity.User;
import com.furidaweb.server.service.auth.AuthService;
import com.furidaweb.server.service.auth.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AuthControllerImpl implements AuthController {

    @Autowired
    private final JwtService jwtService;

    @Autowired
    private final AuthService authService;

    @Override
    public ResponseEntity<?> registerUser(SignUpUserDto signUpUserDto) {
        try {
            User registeredUser = authService.signUp(signUpUserDto);
            String jwtToken = jwtService.generateToken(registeredUser);
            return ResponseEntity.ok(new AuthResponse(jwtToken, "User registered successfully!"));
        } catch (IllegalArgumentException e) {
            // Handle the case where the username or email already exists
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new AuthResponse(null, e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new AuthResponse(null, e.getMessage()));
        }
    }

    @Override
    public ResponseEntity<?> authenticateUser(SignInUserDto signInUserDto) {
        try {
            User authenticatedUser = authService.signIn(signInUserDto);
            String jwtToken = jwtService.generateToken(authenticatedUser);
            return ResponseEntity.ok(new AuthResponse(jwtToken, "Login successful!"));
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new AuthResponse(null, e.getMessage()));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new AuthResponse(null, "Invalid credentials"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new AuthResponse(null, e.getMessage()));
        }
    }
}
