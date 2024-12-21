package com.furidaweb.server.service.auth;

import com.furidaweb.server.dto.auth.UpdatePassDto;
import com.furidaweb.server.dto.auth.SignInUserDto;
import com.furidaweb.server.dto.auth.SignUpUserDto;
import com.furidaweb.server.entity.User;

public interface AuthService {

    User signUp(SignUpUserDto input);

    User signIn(SignInUserDto input);

    void updatePass(String username, UpdatePassDto updatePassDto);
}
