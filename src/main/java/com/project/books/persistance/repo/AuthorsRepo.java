package com.project.books.persistance.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.books.persistance.domain.Authors;

@Repository
public interface AuthorsRepo extends JpaRepository<Authors, Long>{

}
