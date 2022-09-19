package com.project.books.persistance.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    @Column(unique = true, nullable = false)
    private String title;

    @Column
    private int totalPages;

    @Column
    private String publishedDate;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "book_authors",
            joinColumns = {@JoinColumn(name = "books_id")},
            inverseJoinColumns = {@JoinColumn(name = "authors_id")}
    )
    private Set<Authors> authors = new HashSet<>();

    // Default constructor
    public Books() {
        super();
    }

    public Books(Long id, String title, int totalPages,Set<Authors> authors, String publishedDate) {
        super();
        this.bookId = id;
        this.title = title;
        this.totalPages = totalPages;
        this.publishedDate = publishedDate;
        this.authors = authors;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public String getPublished_date() {
        return publishedDate;
    }

    public void setPublished_date(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public Set<Authors> getAuthors() {
        return authors;
    }

    public void addedAuthors(Authors author) {
        authors.add(author);
    }

}