package com.librarySystem.demo.controller;

import com.librarySystem.demo.model.Book;
import com.librarySystem.demo.service.Impl.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "book",produces = MediaType.APPLICATION_JSON_VALUE)
public class BookController {
    @Autowired
    private BookServiceImpl bookService;

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable long id){
        Book book =  bookService.getBook(id);
        return ResponseEntity.ok(book);
    }

    @GetMapping
    public ResponseEntity<Iterable<Book>> listBook(){
        Iterable<Book> books = bookService.getBooks();
        return ResponseEntity.ok(books);
    }

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book){
        bookService.createBook(book);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{/id}")
                .buildAndExpand(book.getId())
                .toUri();

        return ResponseEntity.created(location).body(book);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable long id, @RequestBody Book book){
        bookService.updateBook(id,book);
        return ResponseEntity.ok(book);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id){
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }


}
