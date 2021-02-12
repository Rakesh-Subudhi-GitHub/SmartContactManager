package com.smart.control;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smart.dao.UserRepository;
import com.smart.entity.User;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserRepository userRepo;
	
	@RequestMapping("/index")
	public String dashboard(Model model,Principal principal)
	{
		//user email in help of principal
		String UserName = principal.getName();
		
		//print the username(mail)
		System.out.println("UserName is :: "+UserName);//this is the main key of the each user
		
		//use UserRepository and collect all data of (User table) like name,id,mail,about,password,image,role
		User user = userRepo.getUserByUserName(UserName);
		System.out.println("user is :: "+user);
		
		//print all user data in web page
		model.addAttribute("userdata",user);
		
		return "User/user_dashboard";   //re-direct file to User-->user_dashboard
	}//method
	
	//addcontact handler
	@GetMapping("/add_contact")
	public String insertAddContact(Model model)
	{
		model.addAttribute("title","AddContact");
		
		return "User/addContact";
	}
}//class
