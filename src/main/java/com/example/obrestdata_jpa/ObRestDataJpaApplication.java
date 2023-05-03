package com.example.obrestdata_jpa;

import com.example.obrestdata_jpa.Entities.Author;
import com.example.obrestdata_jpa.Entities.Book;
import com.example.obrestdata_jpa.Enums.Country;
import com.example.obrestdata_jpa.Repositories.AuthorRepository;
import com.example.obrestdata_jpa.Repositories.BookRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;
import java.util.ArrayList;

@SpringBootApplication
public class ObRestDataJpaApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ObRestDataJpaApplication.class, args);
        seedDataBase(context);

    }


    public static void seedDataBase(ApplicationContext context) {
        BookRepository bookRepository = context.getBean(BookRepository.class);
        AuthorRepository authorRepository = context.getBean(AuthorRepository.class);

        //CRUD
        // Create books
        Book bookOne = new Book(null, "Java 8", 1000, 100.0, LocalDate.now(), true);
        Book bookTwo = new Book(null, "Clean Code", 464, 40.00, LocalDate.of(2008, 8, 1), true);
        Book bookThree = new Book(null, "The Pragmatic Programmer", 352, 50.0, LocalDate.of(1999, 10, 20), true);

        // Create authors
        Author authorOne = new Author(null, "Robert C. Martin", LocalDate.of(1952, 12, 5), LocalDate.of(1999, 5, 6), Country.USA.getUrl());
        Author authorTwo = new Author(null, "Joshua Bloch", LocalDate.of(1961, 8, 28), null, Country.USA.getUrl());
        Author authorThree = new Author(null, "Andrew Hunt", LocalDate.of(1964, 3, 4), null, Country.USA.getUrl());

        ArrayList<Author> authorsList = new ArrayList<>();
        authorsList.add(authorOne);
        authorsList.add(authorTwo);
        authorsList.add(authorThree);

        // Save in DB
        authorRepository.saveAll(authorsList);

        ArrayList<Book> booksList = new ArrayList<>();
        booksList.add(bookOne);
        booksList.add(bookTwo);
        booksList.add(bookThree);

        // Save books
        bookRepository.saveAll(booksList);

        // Associate authors with books
        bookOne.setAuthor(authorOne);
        bookTwo.setAuthor(authorOne);
        bookThree.setAuthor(authorThree);

        //Update books
        bookRepository.save(bookOne);
        bookRepository.save(bookTwo);
        bookRepository.save(bookThree);
    }
}