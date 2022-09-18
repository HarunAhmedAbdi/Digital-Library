package com.project.books.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import com.project.books.exception.BookNotFoundException;
import com.project.books.persistance.domain.Authors;
import com.project.books.persistance.domain.Books;
import com.project.books.persistance.repo.AuthorsRepo;
import com.project.books.persistance.repo.BooksRepo;

@Service
public class BooksService {

    private BooksRepo repo;

    private AuthorsRepo authorRepo;

    public BooksService(BooksRepo repo, AuthorsRepo authorRepo) {
        super();
        this.repo = repo;
        this.authorRepo = authorRepo;
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
    // // // Remove existing Person with matching 'id'
    // // this.book.remove(id);
    // // // Add new Person in its place
    // // this.book.add(id, Books);
    // // // Return updated Person from List
    // // return this.book.get(id);
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

    public Books addAuthorToBook(Long bookId, Long authorId) {

        Authors author = this.authorRepo.findById(bookId).get();
        Books book = this.repo.findById(bookId).get();

        book.addedAuthors(author);

        return this.repo.save(book);
    }

    public Books readById(Long bookId) {
        Books found = this.repo.findById(bookId).orElseThrow(BookNotFoundException::new);
        return this.repo.save(found);
    }

    public Optional<Books> findById(Long bookId) {
        return this.repo.findById(bookId);
    }
}