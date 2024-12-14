package com.furidaweb.server.controller;

import com.furidaweb.server.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/api/users")
public interface UserController {

    @GetMapping
    List<User> getAllUsers();
}
