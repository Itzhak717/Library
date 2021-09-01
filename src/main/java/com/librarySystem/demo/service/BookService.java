package com.librarySystem.demo.service;

import com.librarySystem.demo.model.Book;

import java.util.List;

public interface BookService {

    List<Book> getBooksByName(String name);

    Book getBookById(String id);

    List<Book> getBooksByAuthor(String author);

    List<Book> getBooksByPublisher(String publisher);

    List<Book> getBooks();

    Book createBook(Book book);

    Book updateBook(String id, Book book);

    void deleteBook(String id);

    void borrowedBook(String id);

    void returnBook(String id);
}
