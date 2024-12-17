package com.furidaweb.server.service.user;

import com.furidaweb.server.dto.user.UserResponseDto;
import com.furidaweb.server.entity.User;

import java.util.List;

public interface UserService {

    List<UserResponseDto> getAllUsers();

    UserResponseDto getUserById(int id);

    UserResponseDto getUserByUsername(String username);

    User updateUserDetails(int id, User user);

    void deleteUserById(String username, int id);

    void deleteAllUsers();
}
