package com.example.obrestdata_jpa.Controllers;

import com.example.obrestdata_jpa.Entities.Book;
import com.example.obrestdata_jpa.Repositories.BookRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // This means that this class is a Controller
public class BookController {
    private BookRepository bookRepository;

    //Inject dependencies:
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    @GetMapping("/test")
    public String index() {
        return "Greetings from Spring Boot!!!";
    }

    //Crud:

    @GetMapping("/api/books")
    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    //@GetMapping("/books/{id}")


    /*
    @PostMapping("/api/books")
    public Book create(Book book) {
        return this.bookRepository.save(book);
    }
     */


}
