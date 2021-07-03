package com.librarySystem.demo.service;

import com.librarySystem.demo.model.User;

public interface UserService {

    User getUser(Long id);

    Iterable<User> getUsers();

    User createUser(User user);

    User updateUser(Long id, User user);

    void deleteUser(Long id);
}
