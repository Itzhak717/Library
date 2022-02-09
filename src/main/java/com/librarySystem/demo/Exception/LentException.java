package com.librarySystem.demo.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class LentException extends RuntimeException{

    public LentException(String message){
        super(message);
    }
}
