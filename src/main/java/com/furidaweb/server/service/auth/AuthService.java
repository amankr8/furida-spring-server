package com.furidaweb.server.service.auth;

import com.furidaweb.server.dto.auth.UpdatePassDto;
import com.furidaweb.server.dto.auth.SignInUserDto;
import com.furidaweb.server.dto.auth.SignUpUserDto;
import com.furidaweb.server.dto.user.UserResponseDto;

public interface AuthService {

    UserResponseDto signUp(SignUpUserDto input);

    UserResponseDto signIn(SignInUserDto input);

    void updatePass(String username, UpdatePassDto updatePassDto);
}
