package com.rk.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.rk.entity.Book;

public interface IBookDAORepository extends CrudRepository<Book, Integer>{

	//create a method
	public Book getBookById(int id);

	
}
