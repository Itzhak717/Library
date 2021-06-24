package com.librarysystem.demo.controller;

import com.librarysystem.demo.model.Book;
import com.librarysystem.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("book")
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/list")
    public String listBook( Model model){
        model.addAttribute("books", bookRepository.findAll());
        return "book/list";
    }

    @GetMapping("/addBook")
    public String getBookAdd(Model model){
        Book book = new Book();
        model.addAttribute("book", book);
        return "book/addBook";
    }

    @PostMapping("")
    public String addBook(Book book){
        bookRepository.save(book);
        return "redirect:/book/list";
    }

    @PostMapping("/bookDelete/{id}")
    public String deleteBook(@PathVariable Long id){
        bookRepository.deleteById(id);
        return "redirect:/book/list";
    }


}
