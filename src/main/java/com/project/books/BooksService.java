package com.project.books;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;



@Service
public class BooksService {
    
    private List<Books> book = new ArrayList<>();

    public Books addBook(Books newBook) {
        // Add new Books
        this.book.add(newBook);
        // Return last created Books from list
        return this.book.get(this.book.size() - 1);
    }

    public List<Books> getAllBooks() {
        // Return whole list of book
        return this.book;
    }

    public Books updateBooks(int id, Books Books) {
        // Remove existing Person with matching 'id'
        this.book.remove(id);
        // Add new Person in its place
        this.book.add(id, Books);
        // Return updated Person from List
        return this.book.get(id);
    }

    public Books deleteBooks(int id) {
        // Remove Books from list and return it
        return this.book.remove(id);
    }
}