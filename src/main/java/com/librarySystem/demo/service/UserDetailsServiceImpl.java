package com.librarySystem.demo.service;

import com.librarySystem.demo.Exception.NotFoundException;
import com.librarySystem.demo.model.User;
import com.librarySystem.demo.service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserServiceImpl userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        try {
            User user = userService.getUser(s);

            return (UserDetails) user;
        }catch (NotFoundException e) {
            throw new UsernameNotFoundException("Username not found.");
        }
    }
}
