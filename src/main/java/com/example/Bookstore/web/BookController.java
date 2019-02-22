package com.example.Bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.Bookstore.domain.Book;
import com.example.Bookstore.domain.BookRepository;

@Controller
public class BookController {
	@Autowired
	private BookRepository repository;
	
	
	//Hakee kirjat
	@RequestMapping(value="/booklist")
	public String books(Model model) {
		model.addAttribute("books", repository.findAll());	
		return "booklist";
	}
	
	
	//Lisää kirja
	@RequestMapping(value="/create")
	public String createBook(Model model) {
		model.addAttribute("book", new Book());
		return "save";
	}
	
	//Tallenna kirja listaan
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public String saveBook(Book book) {
		repository.save(book);
		return "redirect:booklist";
	}
	
	
	//Muokkaa kirjaa
	@RequestMapping(value="/editBook/{id}", method=RequestMethod.GET)
	public String editBook(@PathVariable("id") long id, Model model) {
		model.addAttribute("book", repository.findById(id));
		return "editBook";
	}
	
	//Poista kirja
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public String deleteBook(@PathVariable("id") long id, Model model) {
		repository.deleteById(id);
		return "redirect:/booklist";
	}
	
}
