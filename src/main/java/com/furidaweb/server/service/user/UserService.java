package com.furidaweb.server.service.user;

import com.furidaweb.server.entity.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUserById(int id);

    User updateUserDetails(int id, User user);

    void deleteUserById(int id);

    void deleteAllUsers();
}
