package com.project.books.rest.controller;

import java.util.List;

import javax.websocket.server.PathParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.books.persistance.domain.Authors;
import com.project.books.service.AuthorsService;

@RestController
public class AuthorsController {


    private AuthorsService service;

    public AuthorsController(AuthorsService service) {
        super();
        this.service = service;
    }

    @GetMapping("/test1") 
        public String test() {
            return "Hello World";
        }

    // Create
    @PostMapping("/createAuthor")
    public Authors addAuthor(@RequestBody Authors newAuthor) {
        return this.service.addAuthor(newAuthor);
    }

    // Read
    @GetMapping("/getAllAuthors")
    public List<Authors> getAllAuthors() {
        return this.service.getAllAuthors();
    }

    // Update
    @PutMapping("/updateAuthor")
    public Authors updateAuthors(@PathParam("id") Long id, @RequestBody Authors book) {
        return this.service.updateAuthors(id, book);
    }

    @DeleteMapping("/deleteAuthor/{id}")
    public boolean deleteAuthor(@PathVariable Long id) {
        return this.service.deleteAuthor(id);
    }







    }
