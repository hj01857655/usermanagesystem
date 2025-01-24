package com.example.usermanagesystem.controller;

import com.example.usermanagesystem.entity.User;
import com.example.usermanagesystem.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "index";
    }

    @GetMapping("/user/list")
    public String listUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "user/list";
    }

    @GetMapping("/user/add")
    public String addUserForm() {
        return "user/add";
    }

    @PostMapping("/user/add")
    public String addUser(@Valid @ModelAttribute User user, BindingResult result) {
        if (result.hasErrors()) {
            // 处理验证错误
            return "error"; // 假设有一个错误页面
        }
        boolean isAdded = userService.addUser(user);
        return isAdded ? "redirect:/user/list" : "error";
    }

    @GetMapping("/user/edit/{id}")
    public String editUserForm(@PathVariable int id, Model model) {
        Optional<User> user = userService.getUserById(id);
        user.ifPresent(value -> model.addAttribute("user", value));
        return "user/edit";
    }

    @PostMapping("/user/edit")
    public String editUser(@ModelAttribute User user) {
        boolean isUpdated = userService.updateUser(user);
        if (isUpdated) {
            return "redirect:/user/list";
        } else {
            // 处理更新失败的情况
            return "error"; // 假设有一个错误页面
        }
    }

    @PostMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return "redirect:/user/list";
    }
} 