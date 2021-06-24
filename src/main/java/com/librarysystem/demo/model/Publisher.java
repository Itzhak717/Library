package com.librarysystem.demo.model;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(exclude = {"books"})
public class Publisher implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String publisherName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "publisher")
    private Set<Book> books = new HashSet<>();
}
