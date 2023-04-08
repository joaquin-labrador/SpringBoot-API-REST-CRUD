package com.example.obrestdata_jpa.Controllers;

import ch.qos.logback.classic.Logger;
import com.example.obrestdata_jpa.Entities.Book;
import com.example.obrestdata_jpa.Repositories.BookRepository;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController // This means that this class is a Controller
public class BookController {
    private final Logger logger = (Logger) LoggerFactory.getLogger(BookController.class);
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

    /*
     * HTTP GET: /api/books
     * @return List<Book>
     */
    @GetMapping("/api/books")
    public ResponseEntity<List<Book>> findAll() {
        try {
            List<Book> books = this.bookRepository.findAll();
            if (books.isEmpty()) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.ok(books);
            }
        } catch (Exception e) {
            logger.error("Error al buscar libros: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /*
     * HTTP GET: /api/books/{id}
     * @param id
     * @return Book
     */
    @GetMapping("/api/books/{id}")
    public ResponseEntity<Book> findById(@PathVariable UUID id) {
        try {
            Book book = this.bookRepository.findById(id).orElse(null);
            if (book == null) {
                return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.ok(book);
            }
        } catch (Exception e) {
            logger.error("Error al buscar libro: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    /*
     * HTTP POST: /api/books
     * @param book
     * @return Book
     */
    @PostMapping("/api/books")
    public Book create(@RequestBody Book book) {
        try {
            return this.bookRepository.save(book);
        } catch (Exception e) {
            logger.error("Error al crear libro: " + e.getMessage());
            return null;
        }
    }


}
