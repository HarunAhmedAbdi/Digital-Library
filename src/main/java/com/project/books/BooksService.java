package com.project.books;



import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class BooksService {

    private BooksRepo repo;

    public BooksService(BooksRepo repo) {
        super();
        this.repo = repo;
    }
    

    public Books addBook(Books book) {
        // // Add new Books
        // this.book.add(newBook);
        // // Return last created Books from list
        // return this.book.get(this.book.size() - 1);
        return this.repo.save(book);
    }

    public List<Books> getAllBooks() {
        // // Return whole list of book
        // return this.book;
        return this.repo.findAll();
    }

    // public Books updateBooks(int id, Books newBook) {
    //     // // Remove existing Person with matching 'id'
    //     // this.book.remove(id);
    //     // // Add new Person in its place
    //     // this.book.add(id, Books);
    //     // // Return updated Person from List
    //     // return this.book.get(id);
    // }

    public Books updateBook(Long id, Books newBook) {
        Optional<Books> existingOptional = this.repo.findById(id);
        Books existing = existingOptional.get();

        existing.setBookId(newBook.getBookId());
        existing.setTitle(newBook.getTitle());
        existing.setTotalPages(newBook.getTotalPages());
        existing.setPublished_date(newBook.getPublished_date());

        return this.repo.save(existing);

    }

    public boolean deleteBook(Long id) {
        this.repo.deleteById(id);
        boolean exists = this.repo.existsById(id);
        return !exists;
    }
}