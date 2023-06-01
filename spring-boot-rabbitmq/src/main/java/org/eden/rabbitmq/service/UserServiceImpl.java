package org.eden.rabbitmq.service;

import lombok.RequiredArgsConstructor;
import org.eden.rabbitmq.producer.UserProducer;
import org.eden.web.model.User;
import org.eden.web.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserProducer userProducer;

    private final Random random = new Random();

    @Override
    public User createUser(User user) {
        user.setId(random.nextLong(1, 1000));
        return user;
    }

    @Override
    public String createUserOnQueue(User user) {
        String uuid = UUID.randomUUID().toString();
        userProducer.send(user);
        return uuid;
    }

    @Override
    public List<User> findAll() {
        return List.of();
    }

    @Override
    public User findById(Long id) {
        return null;
    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    @Override
    public String updateUserOnQueue(User user) {
        return null;
    }

    @Override
    public Long deleteUser(Long id) {
        return null;
    }

    @Override
    public String deleteUserOnQueue(Long id) {
        return null;
    }
}
