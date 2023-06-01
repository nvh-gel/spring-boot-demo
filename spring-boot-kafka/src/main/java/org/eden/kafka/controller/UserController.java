package org.eden.kafka.controller;

import lombok.AllArgsConstructor;
import org.eden.kafka.model.User;
import org.eden.kafka.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @PostMapping
    public String createUser(@RequestBody User request) {
        return userService.createUserOnQueue(request);
    }
}
