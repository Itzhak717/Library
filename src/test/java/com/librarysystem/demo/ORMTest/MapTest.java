package com.librarySystem.demo.ORMTest;

import com.librarySystem.demo.model.Author;
import com.librarySystem.demo.model.Book;
import com.librarySystem.demo.model.Publisher;
import com.librarySystem.demo.repository.AuthorRepository;
import com.librarySystem.demo.repository.BookRepository;
import com.librarySystem.demo.repository.PublisherRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

       /* Author author1 = new Author();
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
        bookRepository.save(book2);*/

    }
}
