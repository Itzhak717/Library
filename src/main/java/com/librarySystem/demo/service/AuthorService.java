package com.librarySystem.demo.service;

import com.librarySystem.demo.model.Author;
import com.librarySystem.demo.model.Book;

public interface AuthorService {

    Author getAuthor(long id);

    Author getAuthor(String name);

    Iterable<Author> getAuthors();

    Author createAuthor(Author author);

    Author updateAuthor(long id, Author author);

    void deleteAuthor(long id);
}
