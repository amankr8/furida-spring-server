package com.furidaweb.server.controller;

import com.furidaweb.server.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/roles")
@RestController
public class RoleController {

    @GetMapping
    public List<Role> getAllRoles() {
        return List.of(Role.values());
    }
}
