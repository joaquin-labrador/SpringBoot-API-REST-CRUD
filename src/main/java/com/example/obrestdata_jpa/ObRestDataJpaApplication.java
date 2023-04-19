package com.example.obrestdata_jpa;

import Enums.Country;
import com.example.obrestdata_jpa.Entities.Author;
import com.example.obrestdata_jpa.Entities.Book;
import com.example.obrestdata_jpa.Repositories.AuthorRepository;
import com.example.obrestdata_jpa.Repositories.BookRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;
import java.util.ArrayList;

@SpringBootApplication
public class ObRestDataJpaApplication {

    public static void main(java.lang.String[] args) {
        ApplicationContext context = SpringApplication.run(ObRestDataJpaApplication.class, args);
        BookRepository bookRepository = context.getBean(BookRepository.class);
        AuthorRepository authorRepository = context.getBean(AuthorRepository.class);

        //CRUD
        //Create:
        Book bookOne = new Book(null, "Java 8", 1000, 100.0, LocalDate.now(), true);
        Book bookTwo = new Book(null, "Clean Code", 464, 40.00, LocalDate.of(2008, 8, 1), true);

        Author authorOne = new Author(null, "Robert C. Martin", "Robert Cecil Martin", LocalDate.of(1952, 12, 5), LocalDate.of(1999, 5, 6), Country.USA.getUrl());
        Author authorTwo = new Author(null, "Joshua Bloch", "Joshua Bloch", LocalDate.of(1961, 8, 28), null, Country.USA.getUrl());


        ArrayList<Book> booksList = new ArrayList<>();
        booksList.add(bookOne);
        booksList.add(bookTwo);

        ArrayList<Author> authorsList = new ArrayList<>();
        authorsList.add(authorOne);
        authorsList.add(authorTwo);
        //Save in DB:
        bookRepository.saveAll(booksList);
        authorRepository.saveAll(authorsList);


        //Read:
        System.out.println("Authors: ");
        authorRepository.findAll().forEach(System.out::println);

    }


}
