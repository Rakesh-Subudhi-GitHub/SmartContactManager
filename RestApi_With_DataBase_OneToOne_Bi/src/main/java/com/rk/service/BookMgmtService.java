package com.rk.service;

import java.util.List;

import com.rk.entity.Book;

public  interface  BookMgmtService {

	public List<Book> getAllBooks();

	public Book getBookById(int id);

	public String addBook(Book book);
	
	public String removeBook(int bid);
	
	public String updateBook(Book book,int bid);
}
