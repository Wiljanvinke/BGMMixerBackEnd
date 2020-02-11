package com.example.bgmmixer.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SongNotFoundException extends RuntimeException {
    public SongNotFoundException(String message){
        super(message);
    }

    public SongNotFoundException(long id){
        super("Song not found with id: " + id);
    }

    public SongNotFoundException(String message, Throwable cause){
        super(message, cause);
    }

}
