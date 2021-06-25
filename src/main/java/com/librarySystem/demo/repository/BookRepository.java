package com.librarySystem.demo.repository;

import com.librarySystem.demo.model.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {
}
