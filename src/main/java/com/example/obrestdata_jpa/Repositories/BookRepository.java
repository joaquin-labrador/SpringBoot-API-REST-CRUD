package com.example.obrestdata_jpa.Repositories;

import com.example.obrestdata_jpa.Entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {

}
