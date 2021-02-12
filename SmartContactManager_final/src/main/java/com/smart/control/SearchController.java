package com.smart.control;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.smart.dao.ContactRepository;
import com.smart.dao.UserRepository;
import com.smart.entity.Contact;
import com.smart.entity.User;

@RestController
public class SearchController {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ContactRepository contRepo;
	
	
	//search handler
	@GetMapping("/search/{query}")
	public ResponseEntity<?> search(@PathVariable("query") String query,Principal principal)
	{
		System.out.println("Query is ::::::: "+query);
		
		User user=userRepo.getUserByUserName(principal.getName());//simple user name take.....
		
		List<Contact> contact = contRepo.findByNameContainingAndUser(query, user);
		
		return ResponseEntity.ok(contact);//simple contact search pass 
	
	}//method
}//class
