package com.project.books.rest.controller;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.books.persistance.domain.Authors;
import com.project.books.persistance.repo.AuthorsRepo;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
// @ContextConfiguration(classes =BookService.class)

@AutoConfigureMockMvc
@Transactional

public class AuthorsControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private AuthorsRepo repo;

    private List<Authors> dB = new ArrayList<>();
    // private Authors expectedTestAuthor;
    private Authors testAuthor;
    // private long testAuthorsId;
    private Authors detailsWantedAuthors;

    @BeforeEach
    public void init() {
        List<Authors> books = List.of(
                new Authors(1L, "JK Rowling"),
                new Authors(2L, "JRR Tolkien"));
        dB.addAll(repo.saveAll(books));
        testAuthor = new Authors(3L, "Franz John");
        // testAuthorsId = testAuthor.getAuthorId();
        // expectedTestAuthor = new Authors(testAuthor.getAuthorId(), testAuthor.getFullName());
        detailsWantedAuthors = new Authors(1L, "Janice Mercy");

    }

    @Test
    public void getAllBooksTest() throws Exception {
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.GET, "/getAllAuthors");
        mockRequest.accept(MediaType.APPLICATION_JSON);
        String authors = mapper.writeValueAsString(dB);
        ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();
        ResultMatcher contentMatcher = MockMvcResultMatchers.content().json(authors);
        mvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contentMatcher);
    }

    // @Test
    // public void createBookTest() throws Exception {
    //     MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.POST, "/createAuthor");
    //     mockRequest.contentType(MediaType.APPLICATION_JSON);
    //     mockRequest.content(mapper.writeValueAsString(testAuthor));
    //     mockRequest.accept(MediaType.APPLICATION_JSON);
    //     ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();
    //     ResultMatcher contentMatcher = MockMvcResultMatchers.content()
    //             .json(mapper.writeValueAsString(expectedTestAuthor));

    //     mvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contentMatcher);

    // }

    // @Test
    // public void updateBookTest() throws Exception {
    //     MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.PUT,
    //             "/updateBook/?id=" + detailsWantedAuthors.getAuthorId());
    //     // specifying accept header return type
    //     mockRequest.contentType(MediaType.APPLICATION_JSON);
    //     mockRequest.content(mapper.writeValueAsString(detailsWantedAuthors));
    //     mockRequest.accept(MediaType.APPLICATION_JSON);
    //     ResultMatcher statusMatcher = MockMvcResultMatchers.status().isAccepted();
    //     ResultMatcher contentMatcher = MockMvcResultMatchers.content()
    //             .json(mapper.writeValueAsString(detailsWantedAuthors));

    //     mvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contentMatcher);
    // }

    @Test
    public void deleteAuthorTest() throws Exception {
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.DELETE,
                "/deleteAuthor/" + detailsWantedAuthors.getAuthorId());
        ResultMatcher statusMatcher = MockMvcResultMatchers.status().isAccepted();

    }
}