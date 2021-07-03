package com.librarySystem.demo.service.Impl;

import com.librarySystem.demo.model.User;
import com.librarySystem.demo.repository.UserRepository;
import com.librarySystem.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("id not found."));
    }

    @Override
    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(User user) {
        if (userRepository.findByUserName(user.getUserName()).isPresent()){
            throw new RuntimeException("Username exist.");
        }
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, User user) {
        user.setId(id);

        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        if (userRepository.findById(id).isEmpty()){
            throw new RuntimeException("id not found.");
        }
        userRepository.deleteById(id);
    }
}
