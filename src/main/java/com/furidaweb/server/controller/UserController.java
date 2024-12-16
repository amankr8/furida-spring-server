package com.furidaweb.server.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@RequestMapping("/api/users")
public interface UserController {

    @GetMapping
    ResponseEntity<?> getAllUsers();

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteUser(@PathVariable int id, Principal principal);
}
