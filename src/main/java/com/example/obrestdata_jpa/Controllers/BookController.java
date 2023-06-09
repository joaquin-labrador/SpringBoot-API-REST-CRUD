package com.example.obrestdata_jpa.Controllers;

import ch.qos.logback.classic.Logger;
import com.example.obrestdata_jpa.DTO.BookDTO;
import com.example.obrestdata_jpa.Entities.Book;
import com.example.obrestdata_jpa.Exceptions.InternalServerError;
import com.example.obrestdata_jpa.Services.BookService;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController // This means that this class is a Controller
public class BookController {
    private final Logger log = (Logger) LoggerFactory.getLogger(BookController.class);
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
     * @return ResponseEntity<List<Book>>
     */
    @GetMapping("/api/books")
    @ResponseBody
    public ResponseEntity<List<BookDTO>> findAll() {
        List<BookDTO> books = this.bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    /*
     * HTTP GET: /api/books/{id}
     * @param id
     * @return ResponseEntity<Book>
     */
    @GetMapping("/api/books/{id}")
    @ResponseBody
    public ResponseEntity<BookDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(this.bookService.findById(id));
    }

    /*
     * HTTP POST: /api/books
     * @param book
     * @return ResponseEntity<Book>
     */
    @PostMapping("/api/books")
    @ResponseBody
    public ResponseEntity<Book> create(@RequestBody Book book) {
        Book newBook = this.bookService.save(book);
        if (newBook == null) {
            log.error("Internal Server Error");
            throw new InternalServerError("Book not created, please try again");
        } else {
            return ResponseEntity.ok(newBook);
        }
    }

    /*
     * HTTP PUT: /api/books/{id}
     * @param id
     * @param book
     * @return ResponseEntity<Book>
     */
    @PutMapping("/api/books/{id}")
    @ResponseBody
    public ResponseEntity<Book> update(@PathVariable UUID id, @RequestBody Book book) {
        Book bookToUpdate = this.bookService.update(id, book);
        return ResponseEntity.ok(bookToUpdate);
    }

    /*
     * HTTP DELETE: /api/books/{id}
     * @param id
     * @return ResponseEntity<Void>
     */
    @DeleteMapping("/api/books/{id}")
    @ResponseBody
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        this.bookService.delete(id);
        log.info("Book deleted with id: " + id);
        return ResponseEntity.noContent().build();
    }

    /*
     * HTTP DELETE: /api/books
     * @return ResponseEntity<Void>
     */
    @DeleteMapping("/api/books")
    @ResponseBody
    public ResponseEntity<Void> deleteAll() {
        this.bookService.deleteAll();
        log.info("All books deleted");
        return ResponseEntity.noContent().build();
    }

}
