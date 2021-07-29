package com.librarySystem.demo.repository;

import com.librarySystem.demo.model.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepository extends MongoRepository<Category,String> {
    Optional<Category> findByCategory(String category);
}
