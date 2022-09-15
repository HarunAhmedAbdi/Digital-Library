package com.project.books.persistance.domain;



import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
    private Date publishedDate;

    // Default constructor
    public Books() {
        super();
    }

    public Books(Long id, String title, int totalPages, Date publishedDate) {
        super();
        this.bookId = id;
        this.title = title;
        this.totalPages = totalPages;
        this.publishedDate = publishedDate;
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

    public Date getPublished_date() {
        return publishedDate;
    }

    public void setPublished_date(Date publishedDate) {
        this.publishedDate = publishedDate;
    }


}