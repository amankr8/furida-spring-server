package com.furidaweb.server.controller.impl;

import com.furidaweb.server.controller.UserController;
import com.furidaweb.server.entity.User;
import com.furidaweb.server.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserControllerImpl implements UserController {

    @Autowired
    private final UserService userService;

    @Override
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
