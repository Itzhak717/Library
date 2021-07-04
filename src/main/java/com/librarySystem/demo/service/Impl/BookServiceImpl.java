package com.librarySystem.demo.service.Impl;

import com.librarySystem.demo.model.Book;
import com.librarySystem.demo.repository.BookRepository;
import com.librarySystem.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book getBook(long id) {
        return bookRepository.findById(id).orElseThrow(() -> new  RuntimeException("not found."));
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
        book.setId(id);
        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(long id) {
        if (bookRepository.findById(id).isEmpty()){
            throw new RuntimeException("not found");
        }
        bookRepository.deleteById(id);
    }
}
