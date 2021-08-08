package com.librarySystem.demo.service.Impl;

import com.librarySystem.demo.Exception.NotFoundException;
import com.librarySystem.demo.model.Book;
import com.librarySystem.demo.repository.BookRepository;
import com.librarySystem.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> getBooksByName(String name) {
        if (bookRepository.findByBookNameLike(name).isEmpty()){
            throw new NotFoundException("Book Not Found!");
        }
        return bookRepository.findByBookNameLike(name);
    }

    @Override
    public Book getBookById(String id) {
        return bookRepository.findById(id).orElseThrow(() -> new NotFoundException("Book id not found!"));
    }

    @Override
    public List<Book> getBooksByAuthor(String author) {
        if (bookRepository.findByAuthors(author).isEmpty()){
            throw new NotFoundException("Author Not Found!");
        }
        return bookRepository.findByAuthors(author);
    }

    @Override
    public List<Book> getBooksByPublisher(String publisher) {
        if (bookRepository.findByPublisherLike(publisher).isEmpty()){
            throw new NotFoundException("publisher Not Found!");
        }
        return bookRepository.findByPublisherLike(publisher);
    }

    @Override
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book createBook(Book book) {
        return bookRepository.insert(book);
    }

    @Override
    public Book updateBook(String  id, Book book) {
        getBooksByName(id);
        book.setId(id);
        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(String id) {
        getBooksByName(id);
        bookRepository.deleteById(id);
    }
}
