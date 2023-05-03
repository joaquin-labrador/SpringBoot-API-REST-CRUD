package com.example.obrestdata_jpa.Services;

import ch.qos.logback.classic.Logger;
import com.example.obrestdata_jpa.DTO.AuthorDTO;
import com.example.obrestdata_jpa.Entities.Author;
import com.example.obrestdata_jpa.Entities.Book;
import com.example.obrestdata_jpa.Error.BadRequestException;
import com.example.obrestdata_jpa.Error.NotFoundException;
import com.example.obrestdata_jpa.Repositories.AuthorRepository;
import com.example.obrestdata_jpa.Repositories.BookRepository;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final Logger log = (Logger) LoggerFactory.getLogger(BookService.class);

    public AuthorService(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }


    /*
     * @return List<Author>
     */
    public List<AuthorDTO> findAll() {
        List<AuthorDTO> authorDTOList = this.authorRepository.findAll().stream().map(AuthorDTO::new).toList();
        if (authorDTOList.isEmpty()) {
            throw new NotFoundException("Author not found");
        }
        return authorDTOList;
    }

    /*
     * @params id
     * @return Author or Null
     */
    public AuthorDTO findById(Long id) {
        Author author = this.authorRepository.findById(id).orElse(null);
        if (author == null) {
            throw new NotFoundException("Author not found");
        }
        return new AuthorDTO(author);
    }

    /*
     * @params author
     * @return Author or Null
     */
    public Author save(Author author) {
        try {
            if (author.getId() != null) {
                log.warn("Not create a author with an id");
                throw new BadRequestException("The id created automatically, you can't set it");
            }
            return this.authorRepository.save(author);
        } catch (DataIntegrityViolationException exception) {
            log.warn("The name already exist");
            throw new BadRequestException("The author already exist in the data base");
        }
    }

    /*
     * @params author , id
     * @return Author
     */
    public Author update(Author author, Long id) {
        try {

            Author authorToUpdate = this.authorRepository.findById(id).orElse(null);

            if (authorToUpdate == null) {
                log.error("Author not found");
                throw new NotFoundException("Author not found");
            }

            authorToUpdate.setCompleteName(author.getCompleteName());
            authorToUpdate.setBirthDate(author.getBirthDate());
            authorToUpdate.setCountry(author.getCountry());
            authorToUpdate.setDeathDate(author.getDeathDate());

            return this.authorRepository.save(authorToUpdate);
        } catch (DataIntegrityViolationException exception) {
            log.warn("The name already exist");
            throw new BadRequestException("The author already exist in the data base");
        }

    }

    /*
     * @params id
     * @return Void
     */
    public void deleteById(Long id) {
        if (id == null) {
            throw new BadRequestException("Id can't be null");
        }

        if (!this.authorRepository.existsById(id)) {
            throw new NotFoundException("Not found author to delete");
        }

        this.authorRepository.deleteById(id);

    }

    /*
     * @return Void
     */
    public void deleteAll() {
        List<Author> authorList = this.authorRepository.findAll();

        if (authorList.isEmpty()) {
            throw new NotFoundException("Not found author to delete");
        }
        /* Remove all books from the author, to avoid the error of foreign key constraint: "Cannot delete or update a parent row: a foreign key constraint fails"
         *  SET NULL the author of de book and remove the list of books from the author after that delete all authors
         */
        authorList.forEach(author -> {
            List<Book> auhtorBookList = this.bookRepository.findBookByAuthorId(author.getId());
            author.removeBook(auhtorBookList);
        });

        this.authorRepository.deleteAll();


    }
}
