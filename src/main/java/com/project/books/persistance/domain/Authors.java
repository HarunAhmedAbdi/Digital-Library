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
import javax.persistence.ManyToMany;


@Entity
public class Authors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authorId;

    @Column
    private String fullName;


    @ManyToMany(mappedBy = "authors", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<Books> books = new HashSet<>();
    
    // Default constructor
    public Authors() {
        super();
    }
    
    public Authors(Long authorId, String fullName) {
        super();
        this.authorId = authorId;
        this.fullName = fullName;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Set<Books> getBooks() {
        return books;
    }


}
