package com.librarysystem.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String category;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "categories")
    private Set<Book> books = new HashSet<>();
}
