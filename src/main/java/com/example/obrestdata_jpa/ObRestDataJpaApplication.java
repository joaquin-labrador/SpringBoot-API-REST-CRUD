package com.example.obrestdata_jpa;

import com.example.obrestdata_jpa.Entities.Book;
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
        BookRepository bookRepository = context.getBean(BookRepository.class);

        //CRUD
        //Create:
        Book bookOne = new Book(null, "Java 8", 1000, 100.0, LocalDate.now(), true);
        Book bookTwo = new Book(null, "Clean Code", 464, 40.00, LocalDate.of(2008, 8, 1), true);

        ArrayList<Book> booksList = new ArrayList<Book>();
        booksList.add(bookOne);
        booksList.add(bookTwo);
        //Save in DB:
        bookRepository.saveAll(booksList);
    }


}
