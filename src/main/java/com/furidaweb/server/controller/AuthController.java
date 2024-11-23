package com.furidaweb.server.controller;

import com.furidaweb.server.dto.user.SignInUserDto;
import com.furidaweb.server.dto.user.SignUpUserDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auth")
@Validated
public interface AuthController {

    @PostMapping("/signup")
    ResponseEntity<?> registerUser(@Valid @RequestBody SignUpUserDto signUpUserDto);

    @PostMapping("/login")
    ResponseEntity<?> authenticateUser(@Valid @RequestBody SignInUserDto signInUserDto);
}
