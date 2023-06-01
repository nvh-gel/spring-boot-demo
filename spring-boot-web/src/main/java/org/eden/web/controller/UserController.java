package org.eden.web.controller;

import lombok.AllArgsConstructor;
import org.eden.web.model.User;
import org.eden.web.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
public abstract class UserController {

    protected UserService userService;

    @PostMapping
    public String createUser(@RequestBody User user) {
        return userService.createUserOnQueue(user);
    }

    @GetMapping
    public List<User> findAllUser() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public User findUserById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PutMapping
    public String updateUser(@RequestBody User user) {
        return userService.updateUserOnQueue(user);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        return userService.deleteUserOnQueue(id);
    }
}
