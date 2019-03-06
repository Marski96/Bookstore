package com.example.Bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.Bookstore.domain.Book;
import com.example.Bookstore.domain.BookRepository;
import com.example.Bookstore.domain.Category;
import com.example.Bookstore.domain.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(BookRepository BookRepo, CategoryRepository CatRepo) {
		return (args)-> {
			
			log.info("SAVING CATEGORIES");
			CatRepo.save(new Category("War"));
			CatRepo.save(new Category("Animals"));
			CatRepo.save(new Category("Math"));
			CatRepo.save(new Category("Psychology"));
			CatRepo.save(new Category("Chemistry"));
			CatRepo.save(new Category("IT"));
			CatRepo.save(new Category("Nature"));
			
			
			log.info("SAVING BOOKS");
			BookRepo.save(new Book (0, "A Farewell to Arms", "Ernest Hemingway", 1929, "1232323-21", 30.00, CatRepo.findBycatName("War").get(0)));
			BookRepo.save(new Book (0, "Animal Farm", "George Orwell", 1945, "22112343-5", 20.00, CatRepo.findBycatName("Animals").get(0)));
			
			log.info("FETCH ALL BOOKS");
			for(Book book : BookRepo.findAll()) {
				log.info(book.toString());
			}
			
			
			
		};
	}

}


