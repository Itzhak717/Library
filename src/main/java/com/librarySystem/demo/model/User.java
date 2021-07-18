package com.librarySystem.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String name;

    private String password;

    private String telephone;

    private String email;

    @Column(columnDefinition = "enum('male','female')")
    private String sex;
}
