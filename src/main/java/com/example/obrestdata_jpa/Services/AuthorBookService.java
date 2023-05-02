package com.example.obrestdata_jpa.Services;

import com.example.obrestdata_jpa.DTO.AuthorBookDTO;
import com.example.obrestdata_jpa.DTO.AuthorDTO;
import com.example.obrestdata_jpa.DTO.BookDTO;
import com.example.obrestdata_jpa.Entities.Author;
import com.example.obrestdata_jpa.Repositories.AuthorRepository;
import com.example.obrestdata_jpa.Repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorBookService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public AuthorBookService(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    public List<AuthorBookDTO> getAllAuthorsWithBooks() {
        List<Author> authors = this.authorRepository.findAll();
        List<AuthorBookDTO> authorBookDTOList = authors.stream().map(author -> {
                    AuthorDTO authorDTO = new AuthorDTO(author);
                    List<BookDTO> bookDTOList = bookRepository.findBookByAuthorId(author.getId())
                            .stream()
                            .map(BookDTO::new)
                            .toList();
                    return new AuthorBookDTO(authorDTO, bookDTOList);
                })
                .toList();
        System.out.println(authorBookDTOList);
        return authorBookDTOList;

    }
}
