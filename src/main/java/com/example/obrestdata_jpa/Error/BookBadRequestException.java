package com.example.obrestdata_jpa.Error;

public class BookBadRequestException extends RuntimeException {
    public BookBadRequestException(String message) {
        super(message);
    }
}
