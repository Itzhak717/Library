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
    public User findById(Long id) {

        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isEmpty()){
            throw new RuntimeException("User Not Found For ID:" + id.toString());
        }

        return userOptional.get();
    }
}
