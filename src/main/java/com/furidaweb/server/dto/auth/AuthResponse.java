package com.furidaweb.server.dto.auth;

import com.furidaweb.server.dto.user.UserResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {

    private String token;
    private UserResponseDto user;
    private String message;
}
