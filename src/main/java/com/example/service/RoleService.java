package com.example.service;

import com.example.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    Role getRole(Long id);
    Role findByRole(Long id);
    List<Role> getAllRoles();
}
