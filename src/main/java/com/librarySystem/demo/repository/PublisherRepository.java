package com.librarySystem.demo.repository;

import com.librarySystem.demo.model.Publisher;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PublisherRepository extends CrudRepository<Publisher, Long> {
    Optional<Publisher> findByPublisherName(String name);
}
