package com.example.bgmmixer.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PlaylistNotFoundException extends RuntimeException {
    public PlaylistNotFoundException(String message){
        super(message);
    }

    public PlaylistNotFoundException(String message, Throwable cause){
        super(message, cause);
    }
}