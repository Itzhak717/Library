package com.librarysystem.demo.service;

import com.librarysystem.demo.model.User;

public interface UserService {

    User findById(Long id);
}
