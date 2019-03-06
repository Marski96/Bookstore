package com.example.Bookstore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.Bookstore.domain.Book;
import com.example.Bookstore.domain.BookRepository;
import com.example.Bookstore.domain.CategoryRepository;

@Controller
public class BookController {
	@Autowired
	private BookRepository BookRepo;
	
	@Autowired
	private CategoryRepository CatRepo;
	
	//Sisäänkirjautuminen
	@RequestMapping(value="/login")
	public String login() {
		return "login";
	} 
	
	//Uloskirjautuminen
	@RequestMapping(value="/logout")
	public String logout() {
		return "redirect:login";
	}   
	
	//Show books
	@RequestMapping(value="/booklist")
	public String books(Model model) {
		model.addAttribute("books", BookRepo.findAll());	
		return "booklist";
	}
	
	
	//Create a new book
	@RequestMapping(value="/create")
	public String createBook(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("categories", CatRepo.findAll());
		return "save";
	}
	
	//Save a book
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public String saveBook(Book book) {
		BookRepo.save(book);
		return "redirect:booklist";
	}
	
	
	//Edit book
	@RequestMapping(value="/editBook/{id}")
	public String editBook(@PathVariable("id") long id, Model model) {
		model.addAttribute("book", BookRepo.findById(id));
		model.addAttribute("categories", CatRepo.findAll());
		return "editBook";
	}
	
	//Delete book
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')")
	public String deleteBook(@PathVariable("id") long id, Model model) {
		BookRepo.deleteById(id);
		return "redirect:../booklist";
	}
	
	//REST all books JSON
	@RequestMapping(value="/books", method = RequestMethod.GET)
	public @ResponseBody List<Book> bookListRest() {
		return (List<Book>) BookRepo.findAll();
	}
	
	//REST get book with id
	@RequestMapping(value="/books/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") long id) {
		return BookRepo.findById(id);
	}
	
}
