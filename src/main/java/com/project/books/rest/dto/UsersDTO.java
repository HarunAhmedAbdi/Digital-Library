package com.project.books.rest.dto;

import com.project.books.persistance.domain.Users;

public class UsersDTO {


    private Long id;


    private String username;

    // Default constructor
    public UsersDTO() {
        super();
    }
    public UsersDTO(Users user) {
        this.id = user.getUserId();
        this.username = user.getUsername();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    
}
