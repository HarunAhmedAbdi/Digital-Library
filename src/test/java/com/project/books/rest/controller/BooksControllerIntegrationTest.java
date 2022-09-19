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
import com.project.books.persistance.domain.Books;
import com.project.books.persistance.repo.BooksRepo;

//import io.netty.handler.codec.http.HttpMethod;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
//@ContextConfiguration(classes =BookService.class)


@AutoConfigureMockMvc
@Transactional



public class BooksControllerIntegrationTest {

	
	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper mapper;

	@MockBean
	private BooksRepo repo;

	private List<Books> dB = new ArrayList<>();
	private Books expectedTestBook;
	private Books testBook;
	private long testBookId;
	private Books detailsWantedBook;




	
	
	@BeforeEach
	public void init() {
		List<Books> books = List.of(
				new Books(1L, "Jack and the bean", 157,null, "rgrjih"),
				new Books(2L, "Wally west", 200,null,"145-365-256"));
		dB.addAll(repo.saveAll(books));
		testBook = new Books(3L, "The Trail, English Translation", 145-256-125,null, "Franz");
		testBookId = testBook.getBookId();
		expectedTestBook = new Books(testBook.getBookId(), testBook.getTitle(),testBook.getTotalPages(), testBook.getAuthors(),testBook.getPublished_date());
		detailsWantedBook = new Books(1L,"Jack and the bean stoke, second edition", 157,null, "145-263-594");

	}

	
	@Test
	public void getAllBooksTest() throws Exception {
		// mock http request builder
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.GET, "/getAllBooks");
		// specifying accept header return type
		mockRequest.accept(MediaType.APPLICATION_JSON);
		// JSON string for obj mapper
		String books = mapper.writeValueAsString(dB);
		// result matcher
		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();
		ResultMatcher contentMatcher = MockMvcResultMatchers.content().json(books);
		// request and assert
		mvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contentMatcher);
	}

	// @Test
	// public void createBookTest() throws Exception {
	// 	// test object
	// 	// mock request
	// 	MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.POST, "/createBook");
	// 	// specifying accept header return type
	// 	mockRequest.contentType(MediaType.APPLICATION_JSON);
	// 	mockRequest.content(mapper.writeValueAsString(testBook));

	// 	mockRequest.accept(MediaType.APPLICATION_JSON);
	// 	ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();
	// 	ResultMatcher contentMatcher = MockMvcResultMatchers.content()
	// 			.json(mapper.writeValueAsString(expectedTestBook));

	// 	mvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contentMatcher);

	// }

	// @Test
	// public void updateBookTest() throws Exception {
	// 	MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.PUT,
	// 			"/updateBook" + detailsWantedBook.getBookId());
	// 	// specifying accept header return type
	// 	mockRequest.contentType(MediaType.APPLICATION_JSON);
	// 	mockRequest.content(mapper.writeValueAsString(detailsWantedBook));
	// 	mockRequest.accept(MediaType.APPLICATION_JSON);
	// 	ResultMatcher statusMatcher = MockMvcResultMatchers.status().isAccepted();
	// 	ResultMatcher contentMatcher = MockMvcResultMatchers.content()
	// 			.json(mapper.writeValueAsString(detailsWantedBook));

	// 	mvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contentMatcher);
	// }

	@Test
	public void deleteBook() throws Exception {
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.DELETE,
				"/deleteBook" + detailsWantedBook.getBookId());
		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isAccepted();
		

	
}
}