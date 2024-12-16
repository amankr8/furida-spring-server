package com.furidaweb.server.controller.impl;

import com.furidaweb.server.controller.UserController;
import com.furidaweb.server.dto.StatusResponse;
import com.furidaweb.server.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserControllerImpl implements UserController {

    @Autowired
    private final UserService userService;

    @Override
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @Override
    public ResponseEntity<?> deleteUser(int id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok(new StatusResponse(HttpStatus.OK.value(), "User deleted successfully!"));
    }
}
