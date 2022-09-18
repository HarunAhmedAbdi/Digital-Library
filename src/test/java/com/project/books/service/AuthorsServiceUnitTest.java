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
import com.project.books.persistance.repo.AuthorsRepo;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class AuthorsServiceUnitTest {

    @Autowired

    private AuthorsService service;

    @MockBean
    private AuthorsRepo repo;

    @Test
    void testGetById() {
        final Long Id = (long) 1;
        final Optional<Authors> author = Optional.ofNullable(new Authors(Id, "Jack"));

        Mockito.when(this.repo.findById(Id)).thenReturn(author);

        assertThat(this.service.findById(Id)).isEqualTo(author);

        Mockito.verify(this.repo, Mockito.times(1)).findById(Id);
    }

    @Test
    void testGetAllAuthors() {
        final List<Authors> authors = List.of(new Authors(1L, "Jack"),new Authors(2L, "Wally"));

        Mockito.when(this.repo.findAll()).thenReturn(authors);

        assertThat(this.service.getAllAuthors()).isEqualTo(authors);

        Mockito.verify(this.repo, Mockito.times(1)).findAll();
    }

    @Test
    void testUpdate() { // REMEMBER TO OVERRIDE THE equals() METHOD IN YOUR ENTITY

        // GIVEN
        final Long id = (long) 1;
        Authors author = new Authors(id, "Jack");
        Optional<Authors> optionalAuthor = Optional.of(author);
        Authors newAuthor = new Authors(id, "Wally");

        // WHEN
        Mockito.when(this.repo.findById(id)).thenReturn(optionalAuthor);
        Mockito.when(this.repo.save(newAuthor)).thenReturn(newAuthor);

        // ASSERT
        assertThat(this.service.updateAuthors(author.getAuthorId(), author)).isEqualTo(newAuthor);

        // VERIFY
        Mockito.verify(this.repo, Mockito.times(1)).findById(id);
        Mockito.verify(this.repo, Mockito.times(1)).save(newAuthor);
    }

    @Test
    void testDelete() {

        // GIVEN
        final Long id = (long) 1;

        // WHEN
        Mockito.when(this.repo.existsById(id)).thenReturn(false);

        // ASSERT
        assertThat(this.service.deleteAuthor(id)).isEqualTo(true);

        // VERIFY
        Mockito.verify(this.repo, Mockito.times(1)).existsById(id);
    }

}