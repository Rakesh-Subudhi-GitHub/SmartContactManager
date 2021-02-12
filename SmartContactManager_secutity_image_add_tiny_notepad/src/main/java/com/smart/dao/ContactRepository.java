package com.smart.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.smart.entity.Contact;

public interface ContactRepository extends JpaRepository<Contact,Integer> {

	//Pagination ...
	
	//use hql query            user(collection) under User.class take id 
	@Query("from Contact where user.id= :userid")
	public List<Contact> findContactByUser(@Param("userid") int userid);
	
}
