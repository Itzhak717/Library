package com.librarySystem.demo.service.Impl;

import com.librarySystem.demo.model.Author;
import com.librarySystem.demo.model.Book;
import com.librarySystem.demo.model.Category;
import com.librarySystem.demo.model.Publisher;
import com.librarySystem.demo.repository.AuthorRepository;
import com.librarySystem.demo.repository.BookRepository;
import com.librarySystem.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorServiceImpl authorService;

    @Autowired
    private CategoryServiceImpl categoryService;

    @Autowired
    private PublisherServiceImpl publisherService;

    @Override
    public Book getBook(String name) {
        return bookRepository.findByBookName(name).orElseThrow(RuntimeException::new);
    }

    @Override
    public Book getBook(long id) {
        return bookRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public Iterable<Book> getBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(long id, Book book) {
        getBook(id);
        book.setId(id);
        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(long id) {
        getBook(id);
        bookRepository.deleteById(id);
    }
}
