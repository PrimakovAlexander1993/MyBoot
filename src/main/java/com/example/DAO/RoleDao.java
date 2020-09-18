package com.example.DAO;

import com.example.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleDao {
    void addRole(Role role);
    Role getRoleByName(String name);
    Long countRoles(String name);
}

