package com.example.service;

import com.example.DAO.RoleDao;
import com.example.model.Role;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.SplittableRandom;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public Role getRole(Long id) {
        return roleDao.getRole(id);
    }

    @Override
    public Role findByRole(Long id) {
        return roleDao.findByRole(id);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleDao.getAllRoles();
    }
}
