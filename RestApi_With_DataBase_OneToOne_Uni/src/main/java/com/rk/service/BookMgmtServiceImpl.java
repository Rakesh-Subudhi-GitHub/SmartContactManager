package com.rk.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rk.dao.IBookDAORepository;
import com.rk.entity.Book;

@Component
public class BookMgmtServiceImpl implements BookMgmtService{
	
	@Autowired
	IBookDAORepository bookRepo=null;
	boolean flag=false;
	
	@Override
	public List<Book> getAllBooks() {
		List<Book> list=null;
		
		list=(List<Book>) bookRepo.findAll();
		
		//returns 
		return list;
		
	}//method

	@Override
	public Book getBookById(int id) {
		
	Book book=null;

		book=bookRepo.getBookById(id);

		return book;
	}//method

	@Override
	public String addBook(Book book) {
		
		bookRepo.save(book);
		flag=true;
		
		if(flag)
		return "book add sussccfully";
		else
			return "not added some internal problem";
	
	}//method

	@Override
	public String removeBook(int bid) {
	
	bookRepo.deleteById(bid);
		flag=true;
		if(flag)
		return "book is removed sussccfully : ";
		else
			return "book is not removed some intedrnal problem";
		
	}//method

	@Override
	public String updateBook(Book book, int bid) {
		
		bookRepo.save(book);
		
		return "Upadate Sucessfully completed ::::::: ";
	}//method

}//class
