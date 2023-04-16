package com.example.obrestdata_jpa.Services;

import com.example.obrestdata_jpa.Entities.Book;
import com.example.obrestdata_jpa.Repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BookService {

    private BookRepository bookRepository;

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
        return this.bookRepository.save(book);
    }

    /*
     * @param id , book
     * @return Book or null
     */
    public Book update(UUID id, Book book) {
        Book bookToUpdate = this.bookRepository.findById(id).orElse(null);
        if (bookToUpdate != null) {
            bookToUpdate.setTitle(book.getTitle());
            bookToUpdate.setPages(book.getPages());
            bookToUpdate.setPrice(book.getPrice());
            bookToUpdate.setReleaseDate(book.getReleaseDate());
            bookToUpdate.setEbook(book.getEbook());
            return this.bookRepository.save(bookToUpdate);
        }
        return null;
    }

}
