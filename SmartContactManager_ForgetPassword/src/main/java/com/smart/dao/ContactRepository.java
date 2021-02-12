package com.smart.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.smart.entity.Contact;
import com.smart.entity.User;

public interface ContactRepository extends JpaRepository<Contact,Integer> {

	//Pagination ...
	
  //use hql query            user(collection) under User.class take id 
  @Query("from Contact c where c.user.id= :userid")  
  public Page<Contact> findContactByUser(@Param("userid") int userid,Pageable pePageable);
																		//pageable is don't take awt always take Spring 
											//Pageable take 2 obj fast->current page
  															     //2nd->Contact per page=5 take more can take

  //pagination use then query need Alice name

  
  //finder method by "name"
  public List<Contact> findByNameContainingAndUser(String name,User user);//Containing its give the name search stating word to search 
  																//just like rakesh to search click ra then corresponding name also show 
  			//take particular User contact so it take "User user" or "AndUser"in findermethod

}
