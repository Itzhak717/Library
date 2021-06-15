package com.librarysystem.demo.repository;

import com.librarysystem.demo.model.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {
}
