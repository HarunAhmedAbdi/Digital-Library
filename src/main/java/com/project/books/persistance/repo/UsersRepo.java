package com.project.books.persistance.repo;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.project.books.persistance.domain.Users;

@Repository
public interface UsersRepo extends JpaRepository<Users, Long>{

}
