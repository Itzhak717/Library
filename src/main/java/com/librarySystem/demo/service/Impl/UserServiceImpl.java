package com.librarySystem.demo.service.Impl;

import com.librarySystem.demo.model.User;
import com.librarySystem.demo.repository.UserRepository;
import com.librarySystem.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("id not found."));
    }

    @Override
    public User getUser(String username) {
        return userRepository.findByUsername(username).orElseThrow(RuntimeException::new);
    }

    @Override
    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()){
            throw new RuntimeException("Username existed.");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
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
