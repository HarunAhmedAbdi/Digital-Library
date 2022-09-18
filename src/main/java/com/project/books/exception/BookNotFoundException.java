package com.project.books.exception;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "A book with that id does not exist")
public class BookNotFoundException extends EntityNotFoundException {
    // TO DO
}
