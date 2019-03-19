package com.example.Bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.Bookstore.domain.Book;
import com.example.Bookstore.domain.BookRepository;
import com.example.Bookstore.domain.Category;

	@RunWith(SpringRunner.class)
	@DataJpaTest
	public class BookRepositoryTest {
		
		@Autowired
		private BookRepository BookRepo;
		
		@Test
		public void findByTitleShouldReturnBook() {
			List<Book> books = BookRepo.findByTitle("A Farewell to Arms");
			
			assertThat(books).hasSize(1);
			assertThat(books.get(0).getAuthor()).isEqualTo("Ernest Hemingway");
		}
		
		@Test
		public void createNewBook() {
			Book book = new Book(0, "Some nice test title", "Some nice test author", 2000, "12345-12345", 20.00, new Category("Testing"));
			BookRepo.save(book);
			assertThat(book.getId()).isNotNull();
		}	
}
