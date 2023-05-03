package com.example.obrestdata_jpa.Controllers;

import ch.qos.logback.classic.Logger;
import com.example.obrestdata_jpa.DTO.AuthorDTO;
import com.example.obrestdata_jpa.Entities.Author;
import com.example.obrestdata_jpa.Error.InternalServerError;
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
    public ResponseEntity<List<AuthorDTO>> findAll() {
        return ResponseEntity.ok(this.authorService.findAll());
    }

    /*
     * HTTP GET: /api/authors/{id}
     * @params id
     * @return ResponseEntity<Author>
     */
    @GetMapping("/api/authors/{id}")
    @ResponseBody
    public ResponseEntity<AuthorDTO> findById(@PathVariable Long id) {
        AuthorDTO author = this.authorService.findById(id);
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
