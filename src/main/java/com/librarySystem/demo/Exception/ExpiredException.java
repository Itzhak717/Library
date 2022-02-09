package com.librarySystem.demo.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class ExpiredException extends RuntimeException{
    public ExpiredException(String message){
        super(message);
    }
}
