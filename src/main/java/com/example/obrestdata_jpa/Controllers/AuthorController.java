package com.example.obrestdata_jpa.Controllers;

import ch.qos.logback.classic.Logger;
import com.example.obrestdata_jpa.Entities.Author;
import com.example.obrestdata_jpa.Error.InternalServerError;
import com.example.obrestdata_jpa.Error.NotFoundException;
import com.example.obrestdata_jpa.Services.AuthorService;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthorController {

    private final AuthorService authorService;
    private final Logger log = (Logger) LoggerFactory.getLogger(BookController.class);

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    /*
     * HTTP GET: /api/authors
     * @return ResponseEntity<List<Author>>
     */
    @GetMapping("/api/authors")
    @ResponseBody
    public ResponseEntity<List<Author>> findAll() {

        List<Author> authors = this.authorService.findAll();
        if (authors.isEmpty()) {
            log.warn("Authors not found");
            throw new NotFoundException("Authors not found");
        } else {
            return ResponseEntity.ok(authors);
        }

    }

    /*
     * HTTP GET: /api/authors/{id}
     * @params id
     * @return ResponseEntity<Author>
     */
    @GetMapping("/api/authors/{id}")
    @ResponseBody
    public ResponseEntity<Author> findById(@PathVariable Long id) {

        Author author = this.authorService.findById(id);
        if (author == null) {
            log.warn("Author not found");
            throw new NotFoundException("Author not found");
        }
        return ResponseEntity.ok(author);


    }

    /*
     * HTTP POST: /api/authors
     * @params author
     * @return ResponseEntity<Author>
     */
    @PostMapping("/api/authors")
    @ResponseBody
    public ResponseEntity<Author> create(@RequestBody Author author) {
        Author newAuthor = this.authorService.save(author);
        if (newAuthor == null) {
            log.warn("Internal Server Error");
            throw new InternalServerError("Author not created");
        }
        return ResponseEntity.ok(author);


    }

    /*
     * HTTP PUT: /api/authors/{id}
     * @params author, id
     * @return ResponseEntity<Author>
     */
    @PutMapping("/api/authors/{id}")
    @ResponseBody
    public ResponseEntity<Author> update(@RequestBody Author author, @PathVariable Long id) {
        Author updateAuthor = this.authorService.update(author, id);
        return ResponseEntity.ok(updateAuthor);
    }


    /*
     * HTTP DELETE: /api/authors/{id}
     * @params id
     * @return ResponseEntity<Void>
     */
    @DeleteMapping("/api/authors/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        this.authorService.deleteById(id);
        log.warn("Delete author by id: " + id);
        return ResponseEntity.noContent().build();
    }

    /*
     *  HTTP DELETE: /api/authors
     * @return ResponseEntity<Void>
     */
    @DeleteMapping("/api/authors")
    @ResponseBody
    public ResponseEntity<Void> deleteAll() {
        this.authorService.deleteAll();
        log.info("Delete all author of the database");
        return ResponseEntity.noContent().build();

    }


}
