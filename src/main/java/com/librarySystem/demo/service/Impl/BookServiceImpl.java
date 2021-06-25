package com.librarySystem.demo.service.Impl;

import com.librarySystem.demo.repository.BookRepository;
import com.librarySystem.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

}
