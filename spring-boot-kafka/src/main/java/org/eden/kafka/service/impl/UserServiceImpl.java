package org.eden.kafka.service.impl;

import lombok.AllArgsConstructor;
import org.eden.kafka.model.User;
import org.eden.kafka.producer.UserProducer;
import org.eden.kafka.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserProducer userProducer;

    private final Random random = new Random();

    @Override
    public User createUser(User user) {
        // Create user code
        user.setId(random.nextLong(1, 1000));
        return user;
    }

    @Override
    public String createUserOnQueue(User user) {
        return userProducer.send(user);
    }
}
