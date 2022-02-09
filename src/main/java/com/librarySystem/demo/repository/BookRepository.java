package com.librarySystem.demo.repository;

import com.librarySystem.demo.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book, String> {
    Page<Book> findByTitleLikeIgnoreCase(String name, Pageable pageable);

    Page<Book> findByPublisherLikeIgnoreCase(String name, Pageable pageable);

    Page<Book> findByAuthorsLikeIgnoreCase(String name,Pageable pageable);
}
