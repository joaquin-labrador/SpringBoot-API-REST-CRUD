package com.example.obrestdata_jpa.Services;

import ch.qos.logback.classic.Logger;
import com.example.obrestdata_jpa.DTO.BookDTO;
import com.example.obrestdata_jpa.Entities.Book;
import com.example.obrestdata_jpa.Exceptions.BadRequestException;
import com.example.obrestdata_jpa.Exceptions.NotFoundException;
import com.example.obrestdata_jpa.Repositories.BookRepository;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BookService {

    private final Logger log = (Logger) LoggerFactory.getLogger(BookService.class);

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    //CRUD:

    /*
     * @return List<Book>
     */
    public List<BookDTO> getAllBooks() {
        List<BookDTO> listBookDTO = this.bookRepository.findAll().stream().map(BookDTO::new).toList();
        if (listBookDTO.isEmpty()) {
            log.warn("The list of books is empty");
            throw new NotFoundException("The list of books is empty");
        }
        return listBookDTO;
    }

    /*
     * @param id
     * @return Book
     */

    public BookDTO findById(UUID id) {
        Book book = this.bookRepository.findById(id).orElse(null);
        if (book == null) {
            log.warn("The book with id: " + id + " not found");
            throw new NotFoundException("Book not found");
        }
        return new BookDTO(book);
    }

    /*
     * @param book
     * @return Book
     */
    public Book save(Book book) {
        try {
            if (book.getId() != null) {
                log.warn("Trying to create a book with an id");
                throw new BadRequestException("The id created automatically, you can't set it");
            }

            return this.bookRepository.save(book);
        } catch (DataIntegrityViolationException ex) {
            log.warn("This book already exist in the data base");
            throw new BadRequestException("This book already exist in the data base");
        }
    }

    /*
     * @params id , book
     * @return Book
     */
    public Book update(UUID id, Book book) {
        try {
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
        } catch (DataIntegrityViolationException ex) {
            log.warn("This book already exist in the data base");
            throw new BadRequestException("This book already exist in the data base");
        }

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
