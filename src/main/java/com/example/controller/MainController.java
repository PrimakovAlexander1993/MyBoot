package com.example.controller;

import com.example.service.RoleService;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.example.model.User;

import java.util.Collections;

@Controller
public class MainController {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @GetMapping("login")
    public String login() {
        return "login";
    }

    @GetMapping("user")
    public String user(ModelMap modelMap, Authentication auth) {
        String userName = auth.getName();
        User user = userService.getUserByName(userName);
        modelMap.addAttribute("user", user);
        return "user";
    }

    @GetMapping("admin")
    public String showUsersTable(ModelMap model) {
        model.addAttribute("users", userService.getAllUsers());
        return "index";
    }

    @GetMapping("signup")
    public String showSignUpForm(User user, ModelMap model) {
        return "add-user";
    }

    @PostMapping("adduser")
    public String addUser(@RequestParam(value = "role_id") Long roleId,
                          @Validated User user, BindingResult result,
                          ModelMap model) {
        if (result.hasErrors()) {
            return "add-user";
        }
        user.setRoles(Collections.singleton(roleService.findByRole(roleId)));
        userService.addUser(user);
        model.addAttribute("users", userService.getAllUsers());
        return "index";
    }


    @GetMapping("edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "update-user";
    }

    @PostMapping("update/{id}")
    public String updateUser(@RequestParam(value = "role_id") Long roleId,
                             @PathVariable("id") long id, @Validated User user,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            user.setId(id);
            return "update-user";
        }
        user.setRoles(Collections.singleton(roleService.findByRole(roleId)));
        userService.updateUser(user);
        model.addAttribute("users", userService.getAllUsers());
        return "index";
    }

    @GetMapping("delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        User user = userService.findById(id);
        userService.deleteUser(user);
        model.addAttribute("users", userService.getAllUsers());
        return "index";
    }
}

