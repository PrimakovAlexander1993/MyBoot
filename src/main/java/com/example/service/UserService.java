package com.example.service;

import com.example.model.Role;
import com.example.model.User;

import java.util.List;
import java.util.Set;

public interface UserService {
    void addUser(User user, List<String> rolesValues);
    void updateUser(User user, List<String> rolesValues);
    void removeUser(int id);
    User getUserById(int id);
    List<User> listUsers();
}
