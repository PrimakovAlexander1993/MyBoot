package com.example.controller;

import com.example.model.User;

import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    private UserService userService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users")
    public String getAllUsers(Model model) {
        model.addAttribute("user", new User()); // может не кладу сюда в конструктор? НЕ приходит
        model.addAttribute("users", userService.listUsers());
        model.addAttribute("ROLES", Arrays.asList("ROLE_USER", "ROLE_ADMIN"));
        return "/admin/users";
    }

    @PostMapping(value = "/users/add")
    public String addUser(@ModelAttribute("user") User user, @RequestParam List<String> rolesValues) {
        if (user.getId() == 0) {
            userService.addUser(user, rolesValues);
        } else {//если add, то id=0,но  ошибка Column 'login' cannot be null
            userService.updateUser(user, rolesValues);
        }//если edit , то Id не ноль, но  ошибка Column 'login' cannot be null
        return "redirect:/admin/users";
    }

    @GetMapping(value = "/users/delete")
    public String deleteUser(@RequestParam("id") int id) {
        this.userService.removeUser(id);
        return "redirect:/admin/users";
    }

    @GetMapping(value = "/editUser")
    public String editUser(@RequestParam("id") int id, Model model) {
        model.addAttribute("user", this.userService.getUserById(id));
        model.addAttribute("users", this.userService.listUsers());
        model.addAttribute("ROLES", Arrays.asList("ROLE_USER", "ROLE_ADMIN"));
        return "/admin/editUser";
    }
}