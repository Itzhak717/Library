package com.librarySystem.demo.service.Impl;

import com.librarySystem.demo.model.Role;
import com.librarySystem.demo.repository.RoleRepository;
import com.librarySystem.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role findByRole(String role) {
        return roleRepository.findByRole(role);
    }
}
