package com.example.obrestdata_jpa.DTO;

import java.util.List;

public class AuthorBookDTO {
    private AuthorDTO author;
    private List<BookDTO> books;

    public AuthorBookDTO() {
    }

    public AuthorBookDTO(AuthorDTO author, List<BookDTO> books) {
        this.author = author;
        this.books = books;
    }

    public AuthorDTO getAuthor() {
        return author;
    }

    public void setAuthor(AuthorDTO author) {
        this.author = author;
    }

    public List<BookDTO> getBooks() {
        return books;
    }

    public void setBooks(List<BookDTO> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "AuthorBookDTO{" + "author=" + author + ", books=" + books + '}';
    }
}
