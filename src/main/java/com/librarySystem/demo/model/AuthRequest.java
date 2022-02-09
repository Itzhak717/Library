package com.librarySystem.demo.model;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Hidden
public class AuthRequest {

    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
