package com.project.books.service;



import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.project.books.persistance.domain.Books;
import com.project.books.persistance.repo.BooksRepo;





@SpringBootTest  //(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class BooksServiceUnitTest {


		
	@Autowired 
		
		private BooksService service;
		
		@MockBean
		private BooksRepo repo;
		
		@Test
		void testGetById() {
			final Long Id = (long) 1;
			final Optional<Books> book = Optional.ofNullable(new Books(Id, "Jack and the bean", 157,null,"145-263-594"));

			Mockito.when(this.repo.findById(Id)).thenReturn(book);

			assertThat(this.service.findById(Id)).isEqualTo(book);

			Mockito.verify(this.repo, Mockito.times(1)).findById(Id);
		}
		
		@Test
		void testGetAllAuthors() {
			final List<Books> books = List.of(new Books(1L, "Jack and the bean", 157,null,"145-263-594"),
					new Books(2L, "", 200,null,"324"));

			Mockito.when(this.repo.findAll()).thenReturn(books);

			assertThat(this.service.getAllBooks()).isEqualTo(books);

			Mockito.verify(this.repo, Mockito.times(1)).findAll();
		}
		
		// @Test
		// void testUpdate() { // REMEMBER TO OVERRIDE THE equals() METHOD IN YOUR ENTITY
		// 	final Long id = (long) 1;
		// 	Books book = new Books(id,"Jack and the bean", 157,null,"2000-12-27");
		// 	Optional<Books> optionalBook = Optional.of(book);

		// 	Books newBook = new Books(id, "Wally west", 200,null,"2020-02-02");

		// 	Mockito.when(this.repo.findById(id)).thenReturn(optionalBook);
		// 	Mockito.when(this.repo.save(newBook)).thenReturn(newBook);

		// 	assertThat(this.service.updateBook(book.getBookId(),newBook)).isEqualTo(newBook);

		// 	Mockito.verify(this.repo, Mockito.times(1)).findById(id);
		// 	Mockito.verify(this.repo, Mockito.times(1)).save(newBook);
		// }

		
		@Test
		void testDelete() {
			final Long id = (long) 1;

			Mockito.when(this.repo.existsById(id)).thenReturn(false);

			assertThat(this.service.deleteBook(id)).isEqualTo(true);

			Mockito.verify(this.repo, Mockito.times(1)).existsById(id);
		}

	}