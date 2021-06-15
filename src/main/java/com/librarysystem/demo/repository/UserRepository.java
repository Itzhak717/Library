package com.librarysystem.demo.repository;

import com.librarysystem.demo.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
