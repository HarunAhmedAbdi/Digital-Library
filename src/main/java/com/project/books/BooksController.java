package com.project.books;

import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BooksController {

    private List<Books> book = new ArrayList<>();

    @GetMapping("/test") 
        public String test() {
            return "Hello World";
        }

    // Create
    @PostMapping("/create")
    public boolean addBooks(@RequestBody Books newbook) {
        return this.book.add(newbook);
    }

    // Read
    @GetMapping("/getAllBooks")
    public List<Books> getAllBooks() {
        return this.book;
    }

    // Update
    @PutMapping("/update")
    public Books updatebook(@PathParam("id") int id, @RequestBody Books book) {
        // Remove existing Person with matching 'id'
        this.book.remove(id);
        // Add new Person in its place
        this.book.add(id, book);
        // Return updated Person from List
        return this.book.get(id);
    }

    @DeleteMapping("/delete/{id}")
    public Books deletbook(@PathVariable int id) {
        // Remove Person and return it
        return this.book.remove(id);
    }








    }
