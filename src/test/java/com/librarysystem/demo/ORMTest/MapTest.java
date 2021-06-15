package com.librarysystem.demo.ORMTest;

import com.librarysystem.demo.model.Author;
import com.librarysystem.demo.model.Book;
import com.librarysystem.demo.model.Publisher;
import com.librarysystem.demo.repository.AuthorRepository;
import com.librarysystem.demo.repository.BookRepository;
import com.librarysystem.demo.repository.PublisherRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

@SpringBootTest
public class MapTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    @Test
    public void add(){

        Author author1 = new Author();
        author1.setAuthorName("Author1");
        authorRepository.save(author1);

        Author author2 = new Author();
        author2.setAuthorName("Author2");
        authorRepository.save(author2);

        Publisher publisher1 = new Publisher();
        publisher1.setPublisherName("Publisher1");
        publisherRepository.save(publisher1);

        //Publisher publisher2 = new Publisher();
        //publisher2.setPublisherName("Publisher2");
        //publisherRepository.save(publisher2);

        Book book1 = new Book();
        book1.setBookName("book1");
        book1.getAuthors().add(author1);
        book1.getAuthors().add(author2);
        book1.setPublisher(publisher1);
        bookRepository.save(book1);

        Book book2 = new Book();
        book2.setBookName("book2");
        book2.getAuthors().add(author2);
        book2.setPublisher(publisher1);
        bookRepository.save(book2);

    }
}
