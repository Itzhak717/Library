package com.librarySystem.demo.service;

import com.librarySystem.demo.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> getBookByName(String name);

    Book getBookById(String id);

    List<Book> getBookByAuthor(String author);

    List<Book> getBookByPublisher(String publisher);

    List<Book> getBooks();

    Book createBook(Book book);

    Book updateBook(String id, Book book);

    void deleteBook(String id);
}
