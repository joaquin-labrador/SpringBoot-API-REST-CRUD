package com.example.obrestdata_jpa.Controllers;

import com.example.obrestdata_jpa.DTO.AuthorBookDTO;
import com.example.obrestdata_jpa.Exceptions.NotFoundException;
import com.example.obrestdata_jpa.Services.AuthorBookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuthorBookController {

    private final AuthorBookService authorBookService;


    public AuthorBookController(AuthorBookService authorBookService) {
        this.authorBookService = authorBookService;
    }

    @GetMapping("/api/authors-books")
    @ResponseBody
    public ResponseEntity<List<AuthorBookDTO>> getAllAuthorsAndBooks() {
        List<AuthorBookDTO> authorBookDTOList = this.authorBookService.getAllAuthorsWithBooks();
        if (authorBookDTOList.isEmpty()) {
            throw new NotFoundException("Authors and books not found");
        } else {
            return ResponseEntity.ok(authorBookDTOList);
        }

    }
}
