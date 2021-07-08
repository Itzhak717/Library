package com.librarySystem.demo.repository;

import com.librarySystem.demo.model.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BookRepository extends CrudRepository<Book, Long> {
    Optional<Book> findByBookName(String name);
}
