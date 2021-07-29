package com.librarySystem.demo.repository;

import com.librarySystem.demo.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends MongoRepository<Book, String> {
    List<Book> findByBookNameLike(String name);

    List<Book> findByPublisherLike(String name);

    List<Book> findByAuthors(String name);

    List<Book> findByCategories(String name);
}
