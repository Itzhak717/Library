package com.librarySystem.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

//@Entity
@Document
@Getter
@Setter
@EqualsAndHashCode(exclude = {"books"})
public class Author{

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String authorName;

    //@ManyToMany(fetch = FetchType.LAZY, mappedBy = "authors")
    //@JsonBackReference
    private Set<Book> books = new HashSet<>();
}
