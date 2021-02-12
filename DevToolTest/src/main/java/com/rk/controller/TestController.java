package com.rk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rk.entity.Book;
import com.rk.service.BookMgmtService;

@RestController
public class TestController {

	@Autowired
	private BookMgmtService service;
	
	@GetMapping("/books")
	public List<Book> getBook()
	{
		//invoke the method and returns
		
		return service.getAllBooks();
		
	}//method
	
	@GetMapping("/books/{id}")
	public Book getBookById(@PathVariable("id") int id)
	{
		//invoke the method
		
		return service.getBookById(id);
		
	}//method
	
	@PostMapping("/books")
	public String addBook(@RequestBody Book book)
	{
		return service.addBook(book);
	}//method
	
	@DeleteMapping("/books/{bookid}")
	public String deleteBook(@PathVariable("bookid") int bid)
	{
		return service.removeBook(bid);
	}//method
	
	@PutMapping("/books/{bookid}")
	public String updateBookById(@RequestBody Book book,@PathVariable("bookid") int bookid)
	{
		return service.updateBook(book,bookid);
	}//method
}//class
