package com.librarySystem.demo.service.Impl;

import com.librarySystem.demo.Exception.NotFoundException;
import com.librarySystem.demo.model.User;
import com.librarySystem.demo.security.SpringUser;
import com.librarySystem.demo.service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserServiceImpl userService;

    @Override
    public UserDetails loadUserByUsername(String s){
        try {
            User user = userService.getUserByEmail(s);

            return new SpringUser(user);
        }catch (NotFoundException e) {
            throw new UsernameNotFoundException("User not found.");
        }
    }
}
