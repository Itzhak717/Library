package com.librarySystem.demo.model;

import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class User implements Serializable{

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

    @ElementCollection(targetClass = UserAuthority.class,fetch = FetchType.EAGER)
    @JoinTable(name = "User_authority", joinColumns = @JoinColumn(name = "username"))
    @Enumerated(value = EnumType.STRING)
    private List<UserAuthority> authorities;
}
