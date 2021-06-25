package com.librarySystem.demo.repository;

import com.librarySystem.demo.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
