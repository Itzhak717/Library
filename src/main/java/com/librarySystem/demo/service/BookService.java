package com.librarySystem.demo.service;

import com.librarySystem.demo.model.Book;

public interface BookService {

    Book getBook(String name);

    Book getBook(long id);

    Iterable<Book> getBooks();

    Book createBook(Book book);

    Book updateBook(long id, Book book);

    void deleteBook(long id);
}
