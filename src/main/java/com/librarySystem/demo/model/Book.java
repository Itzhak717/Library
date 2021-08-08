package com.librarySystem.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Document
@Getter
@Setter
public class Book{

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String  id;

    private String isbn;

    private String introduction;

    private String bookName;

    //@ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.ALL})
    //@JoinColumn(name = "publisher_id")
    private String publisher;

    //@ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    //@JoinTable(name = "book_author", joinColumns = {@JoinColumn(name = "book_id")},
    //        inverseJoinColumns = {@JoinColumn(name = "author_id")})
    private Set<String> authors = new HashSet<>();

    //@ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    //@JoinTable(name = "book_category", joinColumns = {@JoinColumn(name = "book_id")},
    //        inverseJoinColumns = {@JoinColumn(name = "category_id")})
    private Set<String> categories = new HashSet<>();

    private boolean borrowed = false;
}
