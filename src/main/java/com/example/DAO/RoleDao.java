package com.example.DAO;

import com.example.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleDao {
    void addRole(Role role);
    Role getRole(Long id);
    Role findByRole(String role);
    Role findByRole(Long id);
    List<Role> getAllRoles();
    Long countRoles(String name);

}

