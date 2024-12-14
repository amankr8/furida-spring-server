package com.furidaweb.server.controller;

import com.furidaweb.server.entity.Role;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/api/roles")
public interface RoleController {

    @GetMapping
    List<Role> getAllRoles();
}
