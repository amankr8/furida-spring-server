package com.furidaweb.server.controller.impl;

import com.furidaweb.server.controller.RoleController;
import com.furidaweb.server.entity.Role;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RoleControllerImpl implements RoleController {

    @Override
    public List<Role> getAllRoles() {
        return List.of(Role.values());
    }
}
