package com.project.books.service;



import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import com.project.books.persistance.domain.Authors;
import com.project.books.persistance.repo.AuthorsRepo;

@Service
public class AuthorsService {

    private AuthorsRepo repo;

    public AuthorsService(AuthorsRepo repo) {
        super();
        this.repo = repo;
    }
    

    public Authors addAuthor(Authors author) {
        return this.repo.save(author);
    }

    public List<Authors> getAllAuthors() {
        return this.repo.findAll();
    }

    public Authors updateAuthors(Long id, Authors newAuthors) {
        Optional<Authors> existingOptional = this.repo.findById(id);
        Authors existing = existingOptional.get();

        existing.setAuthorId(newAuthors.getAuthorId());
        existing.setFullName(newAuthors.getFullName());

        return this.repo.save(existing);

    }

    public boolean deleteAuthor(Long id) {
        this.repo.deleteById(id);
        boolean exists = this.repo.existsById(id);
        return !exists;
    }
}