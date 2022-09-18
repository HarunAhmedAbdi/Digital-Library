package com.project.books.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import com.project.books.persistance.domain.Authors;
import com.project.books.persistance.domain.Books;
import com.project.books.persistance.repo.AuthorsRepo;
import com.project.books.persistance.repo.BooksRepo;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class BooksServiceUnitTest {

    @Autowired

    private BooksService service;

    @MockBean
    private BooksRepo repo;

    @Test
    void testGetById() {
        final Long Id = (long) 1;
        final Optional<Books> book = Optional.ofNullable(new Books(Id, "rgjhre", 3, "ghr"));

        Mockito.when(this.repo.findById(Id)).thenReturn(book);

        assertThat(this.service.findById(Id)).isEqualTo(book);

        Mockito.verify(this.repo, Mockito.times(1)).findById(Id);
    }

    @Test
    void testGetAllAuthors() {
        final List<Books> book = List.of(new Books(1L,"rgjhre", 3, "ghr"),new Books(2L,"rgjhre", 3, "ghr"));

        Mockito.when(this.repo.findAll()).thenReturn(book);

        assertThat(this.service.getAllBooks()).isEqualTo(book);

        Mockito.verify(this.repo, Mockito.times(1)).findAll();
    }

    @Test
    void testUpdate() { // REMEMBER TO OVERRIDE THE equals() METHOD IN YOUR ENTITY

        // GIVEN
        final Long id = (long) 1;
        Books book = new Books(id, "rgjhre", 3, "ghr");
        Optional<Books> optionalBook = Optional.of(book);
        Books newBook = new Books(id, "rgjhre", 3, "ghr");

        // WHEN
        Mockito.when(this.repo.findById(id)).thenReturn(optionalBook);
        Mockito.when(this.repo.save(newBook)).thenReturn(newBook);

        // ASSERT
        assertThat(this.service.updateBook(book.getBookId(), book)).isEqualTo(newBook);

        // VERIFY
        Mockito.verify(this.repo, Mockito.times(1)).findById(id);
        Mockito.verify(this.repo, Mockito.times(1)).save(newBook);
    }

    @Test
    void testDelete() {

        // GIVEN
        final Long id = (long) 1;

        // WHEN
        Mockito.when(this.repo.existsById(id)).thenReturn(false);

        // ASSERT
        assertThat(this.service.deleteBook(id)).isEqualTo(true);

        // VERIFY
        Mockito.verify(this.repo, Mockito.times(1)).existsById(id);
    }

}