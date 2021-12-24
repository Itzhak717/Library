package com.librarySystem.demo.service;

import com.librarySystem.demo.model.User;

public interface UserService {

    User getUser(Long id);

    User getUser(String username);

    User getUserByEmail(String email);


    Iterable<User> getUsers();

    void createUser(User user);

    void updateUser(Long id, User user);

    void deleteUser(Long id);
}
