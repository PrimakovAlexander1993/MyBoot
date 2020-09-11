package com.example.service;

import com.example.DAO.RoleDao;
import com.example.DAO.UserDaoImpl;
import com.example.model.Role;
import com.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDaoImpl userDao;

    @Autowired
    private RoleDao roleDao;

    //@Autowired
    //private UserRolesDao userRolesDao;

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    @Transactional
    public User findById(Long id) {
        return userDao.findById(id);
    }

    @Override
    @Transactional
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    @Transactional
    public void deleteUser(User user) {
        userDao.deleteUser(user);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    @Transactional
    public User getUserByName(String name) {
        return userDao.getUserByName(name);
    }

    @Override
    @Transactional
    public void addUserRoles(Long userId, String userRoles) {
        userDao.addUserRoles(userId, userRoles);
    }
/*
    @Override
    @Transactional
    public Role findByRole(String role) {
        return roleDao.findByRole(role);
    }

    @Override
    @Transactional
    public Role findByRole(Long id) {
        return roleDao.findByRole(id);
    }*/

    @Override
    @Transactional
    public List<Role> getAllRoles() {
        return roleDao.getAllRoles();
    }
}

