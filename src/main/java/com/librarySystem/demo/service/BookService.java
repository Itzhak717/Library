package com.librarySystem.demo.service;

import com.librarySystem.demo.model.Book;

import java.util.List;

public interface BookService {

    Book getBookById(String id);

    List<Book> getBooks(String name,String search ,String type, int page, String order);

    Book createBook(Book book);

    Book updateBook(String id, Book book);

    void deleteBook(String id);

    void borrowBook(String id);

    void returnBook(String id);
}
