package com.project.books.persistance.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.books.persistance.domain.Books;

@Repository
public interface BooksRepo extends JpaRepository<Books, Long>{
    

}
