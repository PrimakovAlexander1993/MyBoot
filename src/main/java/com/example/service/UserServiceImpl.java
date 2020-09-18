package com.example.service;

import com.example.DAO.RoleDao;
import com.example.DAO.UserDaoImpl;
import com.example.model.Role;
import com.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDaoImpl userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    @Override
    public void addUser(User user, List<String> rolesValues) {
        makeChanges(user, rolesValues);
        userDao.addUser(user);
    }

    @Transactional
    @Override
    public void updateUser(User user, List<String> rolesValues) {
        makeChanges(user, rolesValues);
        userDao.updateUser(user);
    }
    private void makeChanges(User user, List<String> rolesValues) {
        Set<Role> roles = new HashSet<>();
        for (String role: rolesValues) {
            if (roleDao.countRoles(role) > 0) {
                roles.add(roleDao.getRoleByName(role));
            } else {
                Role newRole = new Role();
                newRole.setRole(role);
                roleDao.addRole(newRole);
                roles.add(newRole);
            }
        }
        user.setRoles(roles);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    }

    @Transactional
    @Override
    public void removeUser(int id) {
        userDao.removeUser(id);
    }

    @Transactional
    @Override
    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

    @Transactional
    @Override
    public List<User> listUsers() {
        return userDao.listUsers();
    }


//    @Transactional
//    @Override
//    public void addUser(User user, List<String> rolesValues) {
//        Set<Role> roles = new HashSet<>();
//        for (String role: rolesValues) {
//            if (roleDao.countRoleByName(role) > 0) {
//                roles.add(roleDao.getRoleByName(role));
//            } else {
//                Role newRole = new Role();
//                newRole.setRole(role);
//                //roleDao.save(newRole);
//                roles.add(newRole);
//            }
//        }
//        user.setRoles(roles);
//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//        //userRepository.save(user);
//    }
//    @Transactional
//    @Override
//    public void removeUser(Long id) {
//        userDao.deleteById(id);
//    }
//
//
//
//
//
//    @Override
//    @Transactional
//    public List<User> getAllUsers() {
//        return userDao.getAllUsers();
//    }
//
//    @Override
//    @Transactional
//    public User findById(Long id) {
//        return userDao.findById(id);
//    }
//
//    @Override
//    @Transactional
//    public void addUser(User user) {
//        userDao.addUser(user);
//    }
//
////    @Transactional
////    @Override
////    public void addUser(User user, Set<String> rolesValues) {
////        makeChanges(user, rolesValues);
////        userDao.addUser(user);
////    }
////    @Transactional
////    @Override
////    public void updateUser(User user, Set<String> rolesValues) {
////        makeChanges(user, rolesValues);
////        userDao.updateUser(user);
////    }
//
//    private void makeChanges(User user, Set<String> rolesValues) {
//        Set<Role> roles = new HashSet<>();
//        for (String role: rolesValues) {
//            if (roleDao.countRoles(role) > 0) {
//                roles.add(roleDao.findByRole(role));
//            } else {
//                Role newRole = new Role();
//                newRole.setRole(role);
//                roleDao.addRole(newRole);
//                roles.add(newRole);
//            }
//        }
//        user.setRoles(roles);
//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//    }
//
//    @Override
//    @Transactional
//    public void deleteUser(User user) {
//        userDao.deleteUser(user);
//    }
//
//    @Override
//    @Transactional
//    public void updateUser(User user) {
//        userDao.updateUser(user);
//    }
//
//    @Override
//    @Transactional
//    public User getUserByName(String name) {
//        return userDao.getUserByName(name);
//    }
//
//    @Override
//    @Transactional
//    public void addUserRoles(Long userId, String userRoles) {
//        userDao.addUserRoles(userId, userRoles);
//    }
//
//    @Override
//    @Transactional
//    public List<Role> getAllRoles() {
//        return roleDao.getAllRoles();
//    }
}

