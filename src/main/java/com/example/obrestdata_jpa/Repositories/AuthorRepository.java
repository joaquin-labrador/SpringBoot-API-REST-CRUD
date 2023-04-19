package com.example.obrestdata_jpa.Repositories;

import com.example.obrestdata_jpa.Entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}
