package com.project.books.service;



import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.project.books.persistance.domain.Users;
import com.project.books.persistance.repo.UsersRepo;
import com.project.books.rest.dto.UsersDTO;

@Service
public class UsersService {

    private UsersRepo repo;
    private ModelMapper mapper;

    public UsersService(UsersRepo repo, ModelMapper mapper) {
        super();
        this.repo = repo;
        this.mapper = mapper;
    }

    private UsersDTO mapToDTO(Users user) {
        return this.mapper.map(user, UsersDTO.class);
    }
    public UsersDTO addUser(Users user) {
        Users saved = this.repo.save(user);
        return this.mapToDTO(saved);
    }

    public List<UsersDTO> getAllUsers() {
        return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public UsersDTO updateUser(Long id, Users newUser) {
        Optional<Users> existingOptional = this.repo.findById(id);
        Users existing = existingOptional.get();

        existing.setUserId(newUser.getUserId());
        existing.setUsername(newUser.getUsername());
        existing.setPassword(newUser.getPassword());
        
        Users updated = this.repo.save(existing);

        return this.mapToDTO(updated);

    }

    public boolean deleteUser(Long id) {
        this.repo.deleteById(id);
        boolean exists = this.repo.existsById(id);
        return !exists;
    }
}