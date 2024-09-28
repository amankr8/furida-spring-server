package com.furidaweb.server.dto;

import com.furidaweb.server.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpUserDto {

    private String username;
    private String email;
    private String password;
    private Role role;
}