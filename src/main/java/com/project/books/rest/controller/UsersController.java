package com.project.books.rest.controller;

import java.util.List;

import javax.websocket.server.PathParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.books.persistance.domain.Users;
import com.project.books.service.UsersService;

@RestController
public class UsersController {


    private UsersService service;

    public UsersController(UsersService service) {
        super();
        this.service = service;
    }

    @GetMapping("/test2") 
        public String test() {
            return "Hello World";
        }

    // Create
    @PostMapping("/createUser")
    public Users addUser(@RequestBody Users user) {
        return this.service.addUser(user);
    }

    // Read
    @GetMapping("/getAllUsers")
    public List<Users> getAllUsers() {
        return this.service.getAllUsers();
    }

    // Update
    @PutMapping("/updateUser")
    public Users updateUser(@PathParam("id") Long id, @RequestBody Users user) {
        return this.service.updateUser(id, user);
    }

    @DeleteMapping("/deleteUser/{id}")
    public boolean deleteUser(@PathVariable Long id) {
        return this.service.deleteUser(id);
    }







    }
