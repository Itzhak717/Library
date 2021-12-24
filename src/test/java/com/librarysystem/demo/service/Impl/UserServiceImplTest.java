package com.librarySystem.demo.service.Impl;

import com.librarySystem.demo.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getUser() {
    }

    @Test
    void testGetUser() {
    }

    @Test
    void getUsers() {
    }

    @Transactional
    @Test
    void createUser() {

        //User user = new User();
        //user.setUsername("test user");
        //user.setEmail("test@mail.com");
        //user.setPassword("test1234");
        //user.setSex("male");
        //user.setRole("ADMIN");
        //
        //userService.createUser(user);
    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteUser() {
    }
}