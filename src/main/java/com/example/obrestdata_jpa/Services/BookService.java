package com.example.obrestdata_jpa.Services;

import ch.qos.logback.classic.Logger;
import com.example.obrestdata_jpa.Entities.Book;
import com.example.obrestdata_jpa.Error.BadRequestException;
import com.example.obrestdata_jpa.Error.NotFoundException;
import com.example.obrestdata_jpa.Repositories.BookRepository;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BookService {

    private final Logger log = (Logger) LoggerFactory.getLogger(BookService.class);
    private final BookRepository bookRepository;

    //Inject dependencies:
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    //CRUD:

    /*
     * @return List<Book>
     */
    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    /*
     * @param id
     * @return Book
     */

    public Book findById(UUID id) {
        return this.bookRepository.findById(id).orElse(null);
    }

    /*
     * @param book
     * @return Book
     */
    public Book save(Book book) {
        if (book.getId() != null) {
            log.warn("Trying to create a book with an id");
            throw new BadRequestException("The id created automatically, you can't set it");
        }

        return this.bookRepository.save(book);
    }

    /*
     * @params id , book
     * @return Book
     */
    public Book update(UUID id, Book book) {

        if (id == null) {
            log.warn("Trying to update a book with a null id");
            throw new BadRequestException("The id can't be null");
        }

        Book bookToUpdate = this.bookRepository.findById(id).orElse(null);
        if (bookToUpdate == null) {
            log.warn("The book with id: " + id + " not found");
            throw new NotFoundException("Book not found");
        }
        bookToUpdate.setTitle(book.getTitle());
        bookToUpdate.setPages(book.getPages());
        bookToUpdate.setPrice(book.getPrice());
        bookToUpdate.setReleaseDate(book.getReleaseDate());
        bookToUpdate.setEbook(book.getEbook());
        return this.bookRepository.save(bookToUpdate);


    }


    /*
     * @param id
     * @return book
     * */
    public void delete(UUID id) {
        if (id == null) {
            log.warn("Not have id for found de book to delete");
            throw new BadRequestException("The id can't be null");
        }

        if (!this.bookRepository.existsById(id)) {
            log.warn("The book with id: " + id + " not found");
            throw new NotFoundException("Book not found");
        }

        this.bookRepository.deleteById(id);
    }

    /*
     * @return void
     */
    public void deleteAll() {
        this.bookRepository.deleteAll();
    }
}
