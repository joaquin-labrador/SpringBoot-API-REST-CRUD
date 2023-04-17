package com.example.obrestdata_jpa.Error;

public class BookNotCreateException extends RuntimeException {
    public BookNotCreateException(String message) {
        super(message);
    }
}
