package org.eden.kafka.service;

import org.eden.kafka.model.User;

public interface UserService {

    User createUser(User user);

    String createUserOnQueue(User user);
}
