package com.librarySystem.demo.service.Impl;

import com.librarySystem.demo.Exception.LentException;
import com.librarySystem.demo.Exception.NotFoundException;
import com.librarySystem.demo.Exception.PublicationYearException;
import com.librarySystem.demo.model.Book;
import com.librarySystem.demo.repository.BookRepository;
import com.librarySystem.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book getBookById(String id) {
        return bookRepository.findById(id).orElseThrow(() -> new NotFoundException("Book id not found!"));
    }

    @Override
    public List<Book> getBooks(String name,String search, String type, int page, String order) {

        Sort.Direction orderType = Sort.Direction.ASC;
        Page<Book> pageResult;

        if(search.equals("desc")){
            orderType = Sort.Direction.DESC;
        }

        if(search.equals("author")){
            pageResult = bookRepository.findByAuthorsLikeIgnoreCase(name, PageRequest.of(page, 10, Sort.by(orderType,type)));
        }else if(search.equals("publisher")){
            pageResult = bookRepository.findByPublisherLikeIgnoreCase(name, PageRequest.of(page, 10, Sort.by(orderType,type)));
        }else {
            pageResult = bookRepository.findByTitleLikeIgnoreCase(name, PageRequest.of(page, 10, Sort.by(orderType,type)));
        }

        if (pageResult.isEmpty()) {
            throw new NotFoundException("Book Not Found!");
        }
        return pageResult.getContent();
    }

    @Override
    public Book createBook(Book book) {
        Date date = new Date();

        ZoneId timeZone = ZoneId.systemDefault();
        int year = date.toInstant().atZone(timeZone).toLocalDate().getYear();

        if (Integer.parseInt(book.getPublicationYear()) > year){
            throw new PublicationYearException("Publication Year Can not later than "+year);
        }
        return bookRepository.insert(book);
    }

    @Override
    public Book updateBook(String id, Book book) {
        getBookById(id);
        book.setId(id);
        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(String id) {
        getBookById(id);
        bookRepository.deleteById(id);
    }

    @Override
    public void borrowBook(String id) {
        Book book = getBookById(id);
        if (book.isBorrowed()){
            throw new LentException("Book is lent.");
        }
        book.setBorrowed(true);
        bookRepository.save(book);
    }

    @Override
    public void returnBook(String id) {
        Book book = getBookById(id);
        book.setBorrowed(false);
        bookRepository.save(book);
    }
}
