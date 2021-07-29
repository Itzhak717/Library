package com.librarySystem.demo.repository;

import com.librarySystem.demo.model.Author;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AuthorRepository extends MongoRepository<Author, String> {
    Optional<Author> findAuthorByAuthorName(String name);
}
