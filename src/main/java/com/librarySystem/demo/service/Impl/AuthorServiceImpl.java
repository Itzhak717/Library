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
    public Author getAuthor(long id) {
        return authorRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public Author getAuthor(String name) {
        return authorRepository.findAuthorByAuthorName(name).orElseThrow(RuntimeException::new);
    }

    @Override
    public Iterable<Author> getAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Author createAuthor(Author author) {
        if (getAuthor(author.getAuthorName()) == null){
            return authorRepository.save(author);
        }
        return author;
    }

    @Override
    public Author updateAuthor(long id, Author author) {
        getAuthor(id);
        author.setId(id);
        return authorRepository.save(author);
    }

    @Override
    public void deleteAuthor(long id) {
        getAuthor(id);
        authorRepository.deleteById(id);
    }
}
