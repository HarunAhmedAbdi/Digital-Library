package com.project.books.service;



import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.project.books.persistance.domain.Users;
import com.project.books.persistance.repo.UsersRepo;

@Service
public class UsersService {

    private UsersRepo repo;

    public UsersService(UsersRepo repo) {
        super();
        this.repo = repo;
    }
    

    public Users addUser(Users user) {
        return this.repo.save(user);
    }

    public List<Users> getAllUsers() {
        return this.repo.findAll();
    }

    public Users updateUser(Long id, Users newUsers) {
        Optional<Users> existingOptional = this.repo.findById(id);
        Users existing = existingOptional.get();

        existing.setUserId(newUsers.getUserId());
        existing.setUsername(newUsers.getUsername());
        existing.setPassword(newUsers.getPassword());
        return this.repo.save(existing);

    }

    public boolean deleteUser(Long id) {
        this.repo.deleteById(id);
        boolean exists = this.repo.existsById(id);
        return !exists;
    }
}