package com.librarySystem.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Columns;
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
public class Category{

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    //@Column(unique = true)
    private String category;

    //@ManyToMany(fetch = FetchType.LAZY, mappedBy = "categories")
    //@JsonBackReference
    private Set<Book> books = new HashSet<>();
}
