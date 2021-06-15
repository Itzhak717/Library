package com.librarysystem.demo.service.Impl;

import com.librarysystem.demo.repository.BookRepository;
import com.librarysystem.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;
}
