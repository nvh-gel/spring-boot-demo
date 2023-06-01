package org.eden.rabbitmq.controller;

import org.eden.web.controller.UserController;
import org.eden.web.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("user")
public class RabbitUserController extends UserController {

    public RabbitUserController(UserService userService) {
        super(userService);
    }
}
