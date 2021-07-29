package com.librarySystem.demo.service;

import com.librarySystem.demo.model.Author;

public interface AuthorService {

    Author getAuthorById(String id);

    Author getAuthorByName(String name);

    Iterable<Author> getAuthors();

    Author createAuthor(Author author);

    Author updateAuthor(String id, Author author);

    void deleteAuthor(String id);
}
