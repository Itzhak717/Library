package com.librarySystem.demo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Document
@Schema(description = "Book Information")
@Getter
@Setter
public class Book{

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String  id;

    @NotBlank
    private String isbn;

    private String description;

    @NotBlank
    private String title;

    @NotBlank
    private String publisher;

    @NotBlank
    private Set<String> authors = new HashSet<>();

    @NotBlank
    private String publicationYear;

    private boolean borrowed = false;
}
