package com.librarySystem.demo.repository;

import com.librarySystem.demo.model.Author;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AuthorRepository extends CrudRepository<Author, Long> {
    Optional<Author> findAuthorByAuthorName(String name);
}
