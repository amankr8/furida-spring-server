package com.furidaweb.server.dto.user;

import com.furidaweb.server.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {

    private int id;
    private String username;
    private String email;
    private Role role;
    private Date date;
}
