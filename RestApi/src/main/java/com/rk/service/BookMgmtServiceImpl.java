package com.rk.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.rk.entity.Book;

@Component
public class BookMgmtServiceImpl implements BookMgmtService{

	//create  a ArrayList
			private static  List<Book> list=new ArrayList();
			boolean flag=false;	
			
			static {
			
				//add  book
				list.add(new Book(100,"my sql","raja"));
				list.add(new Book(101,"oracle","raka"));
				list.add(new Book(102,"java","rajesh"));
				list.add(new Book(103,"adv java","laja"));
				list.add(new Book(104,"spring","tyrt"));
				
			}//static
			
	@Override
	public List<Book> getAllBooks() {
		
		//return all book means return list
		return list;
		
	}//method

	@Override
	public Book getBookById(int id) {
		
		//use one more method chek condi
		Book book=null;
		book=list.stream().filter(bookid->bookid.getId()==id).findFirst().get();     //java 8 fetcher
			
		return book;
	}//method

	@Override
	public String addBook(Book book) {
		
		list.add(book);
		flag=true;
		
		if(flag)
		return "book add sussccfully";
		else
			return "not added some internal problem";
	
	}//method

	@Override
	public String removeBook(int bid) {
	
		list=list.stream().filter(book->book.getId()!=bid).												//chek condition if != true then add a new list
																							collect(Collectors.toList());   //that show the collection 
		
		//bid book is not add other are added
		//same list chnge remove indirectly 
		return "book is removed sussccfully : ";
	
	}//method

	@Override
	public String updateBook(Book book, int bid) {
		
		list=list.stream().map(b->{
		                                                 if(b.getId()==book.getId())
		                                                	 {
		                                                	 b.setTitle(book.getTitle());
		                                                	 b.setAuthor(book.getAuthor());
		                                                	 }
		                                                 
		                                                 return b;
		                                                 }	).collect(Collectors.toList());
		
		
		return "Upadate Sucessfully completed ::::::: ";
	}

}//class
