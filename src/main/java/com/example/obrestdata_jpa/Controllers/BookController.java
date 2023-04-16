package com.example.obrestdata_jpa.Controllers;

import ch.qos.logback.classic.Logger;
import com.example.obrestdata_jpa.Entities.Book;
import com.example.obrestdata_jpa.Error.BookNotFoundException;
import com.example.obrestdata_jpa.Services.BookService;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController // This means that this class is a Controller
public class BookController {
    private final Logger logger = (Logger) LoggerFactory.getLogger(BookController.class);
    private final BookService bookService;

    //Inject dependencies:
    public BookController(BookService bookService) {
        this.bookService = bookService;
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
    @ResponseBody
    public ResponseEntity<List<Book>> findAll() {
        List<Book> books = this.bookService.findAll();
        if (books.isEmpty()) {
            throw new BookNotFoundException("No hay libros");
        } else {
            return ResponseEntity.ok(books);
        }

    }

    /*
     * HTTP GET: /api/books/{id}
     * @param id
     * @return Book
     */
    @GetMapping("/api/books/{id}")
    @ResponseBody
    public ResponseEntity<Book> findById(@PathVariable UUID id) {

        Book book = this.bookService.findById(id);
        if (book == null) {
            throw new BookNotFoundException("Libro no encontrado");
        } else {
            return ResponseEntity.ok(book);
        }

    }

    /*
     * HTTP POST: /api/books
     * @param book
     * @return Book
     */
    @PostMapping("/api/books")
    @ResponseBody
    public ResponseEntity<Book> create(@RequestBody Book book) {
        try {
            return ResponseEntity.ok(this.bookService.save(book));
        } catch (Exception e) {
            logger.error("Error al crear libro: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /*
     * HTTP PUT: /api/books/{id}
     * @param id
     * @param book
     * @return Book
     */
    @PutMapping("/api/books/{id}")
    @ResponseBody
    public ResponseEntity<Book> update(@PathVariable UUID id, @RequestBody Book book) {

        Book bookToUpdate = this.bookService.update(id, book);
        if (bookToUpdate == null) {
            throw new BookNotFoundException("Libro no encontrado");
        } else {
            return ResponseEntity.ok(bookToUpdate);
        }

    }

}
