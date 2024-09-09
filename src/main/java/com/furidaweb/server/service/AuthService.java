package com.furidaweb.server.service;

import com.furidaweb.server.dto.SignInUserDto;
import com.furidaweb.server.dto.SignUpUserDto;
import com.furidaweb.server.entity.User;

public interface AuthService {

    User signUp(SignUpUserDto input);

    User signIn(SignInUserDto input);
}
