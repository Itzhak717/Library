package com.librarySystem.demo.service.Impl;

import com.librarySystem.demo.model.Author;
import com.librarySystem.demo.repository.AuthorRepository;
import com.librarySystem.demo.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    AuthorRepository authorRepository;

    @Override
    public Author getAuthorById(String id) {
        return authorRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public Author getAuthorByName(String name) {
        return authorRepository.findAuthorByAuthorName(name).orElseThrow(RuntimeException::new);
    }

    @Override
    public Iterable<Author> getAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Author createAuthor(Author author) {
        if (getAuthorByName(author.getAuthorName()) == null){
            return authorRepository.save(author);
        }
        return author;
    }

    @Override
    public Author updateAuthor(String id, Author author) {
        getAuthorById(id);
        author.setId(id);
        return authorRepository.save(author);
    }

    @Override
    public void deleteAuthor(String id) {
        getAuthorById(id);
        authorRepository.deleteById(id);
    }
}
