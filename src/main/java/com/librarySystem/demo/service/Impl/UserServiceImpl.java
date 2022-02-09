package com.librarySystem.demo.service.Impl;

import com.librarySystem.demo.Exception.ExistException;
import com.librarySystem.demo.Exception.NotFoundException;
import com.librarySystem.demo.model.Role;
import com.librarySystem.demo.model.User;
import com.librarySystem.demo.repository.UserRepository;
import com.librarySystem.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleServiceImpl roleService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("id not found."));
    }

    @Override
    public User getUser(String username) {
        return userRepository.findByUsername(username).orElseThrow(()->new  NotFoundException("Username not found."));
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(()->new  NotFoundException("Email not found."));
    }

    @Override
    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public void createUser(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()){
            throw new ExistException("Username existed.");
        }
        Role userRole = roleService.findByRole("USER");

        Set<Role> roles = new HashSet<>();
        roles.add(userRole);
        for(Role role: user.getRoles()){
            roles.add(roleService.findByRole(role.getRole()));
        }
        user.setRoles(roles);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void updateUser(Long id, User user) {
        user.setId(id);

        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        if (userRepository.findById(id).isEmpty()){
            throw new NotFoundException("id not found.");
        }
        userRepository.deleteById(id);
    }
}
