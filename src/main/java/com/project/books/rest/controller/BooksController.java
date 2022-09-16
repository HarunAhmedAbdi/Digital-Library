package com.project.books.rest.controller;

import java.util.List;

import javax.security.auth.Subject;
import javax.websocket.server.PathParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.books.persistance.domain.Books;
import com.project.books.service.BooksService;

@RestController
public class BooksController {


    private BooksService service;

    public BooksController(BooksService service) {
        super();
        this.service = service;
    }

    @GetMapping("/test") 
        public String test() {
            return "Hello World";
        }

    // Create
    @PostMapping("/createBook")
    public Books addBook(@RequestBody Books newBook) {
        return this.service.addBook(newBook);
    }

    // Read
    @GetMapping("/getAllBooks")
    public List<Books> getAllBooks() {
        return this.service.getAllBooks();
    }

    // Update
    @PutMapping("/updateBook")
    public Books updatebook(@PathParam("id") Long id, @RequestBody Books book) {
        return this.service.updateBook(id, book);
    }

    @DeleteMapping("/deleteBook/{id}")
    public boolean deletbook(@PathVariable Long id) {
        return this.service.deleteBook(id);
    }

    @PutMapping("/books/{bookId}/authors/{authorId}")
    public Books addAuthorToBook(@PathVariable Long bookId, Long authorId){
        return this.service.addAuthorToBook(bookId, authorId);
    }








    }
