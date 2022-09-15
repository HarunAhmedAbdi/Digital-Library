package com.project.books.rest.dto;

import com.project.books.persistance.domain.Users;

public class UsersDTO {


    private Long userId;


    private String username;

    // Default constructor
    public UsersDTO() {
        super();
    }
    public UsersDTO(Users user) {
        this.userId = user.getUserId();
        this.username = user.getUsername();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long id) {
        this.userId = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    
}
