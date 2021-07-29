package com.librarySystem.demo.repository;

import com.librarySystem.demo.model.Publisher;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PublisherRepository extends MongoRepository<Publisher, String> {
    Optional<Publisher> findByPublisherName(String name);
}
