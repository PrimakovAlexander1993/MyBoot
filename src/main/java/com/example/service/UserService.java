package com.example.service;

import com.example.model.Role;
import com.example.model.User;

import java.util.List;
import java.util.Set;

public interface UserService {
    List<User> getAllUsers();

    User findById(Long id);

    //void addUser(User user);
    // void updateUser(User user);

    void deleteUser(User user);
    void addUser(User user, Set<String> rolesValues);
    void updateUser(User user, Set<String> rolesValues);


    User getUserByName(String name);

    void addUserRoles(Long userId, String userRoles);

    List<Role> getAllRoles();
}
