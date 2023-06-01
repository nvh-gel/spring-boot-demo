package org.eden.web.service;

import org.eden.web.model.User;

import java.util.List;

@SuppressWarnings("unused")
public interface UserService {

    User createUser(User user);

    String createUserOnQueue(User user);

    List<User> findAll();

    User findById(Long id);

    User updateUser(User user);

    String updateUserOnQueue(User user);

    Long deleteUser(Long id);

    String deleteUserOnQueue(Long id);

}
