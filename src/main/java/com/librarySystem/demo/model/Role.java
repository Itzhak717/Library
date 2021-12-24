package com.librarySystem.demo.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "enum('ADMIN','USER')")
    private String role;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();
    //ADMIN, USER;
    //
    //public UserAuthority fromString(String key){
    //    return Arrays.stream(values())
    //            .filter(value -> value.name().equalsIgnoreCase(key))
    //            .findFirst()
    //            .orElse(null);
    //}

}
