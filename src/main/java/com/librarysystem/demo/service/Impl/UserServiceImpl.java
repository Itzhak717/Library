package com.librarysystem.demo.service.Impl;

import com.librarysystem.demo.model.User;
import com.librarysystem.demo.repository.UserRepository;
import com.librarysystem.demo.service.UserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

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
