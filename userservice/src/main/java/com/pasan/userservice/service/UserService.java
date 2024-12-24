package com.pasan.userservice.service;

import com.pasan.userservice.model.User;

public interface UserService {
    User registerUser(User user);
    String authenticateUser(String username, String password);
}
