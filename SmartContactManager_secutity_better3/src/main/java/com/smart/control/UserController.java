package com.smart.control;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smart.dao.UserRepository;
import com.smart.entity.Contact;
import com.smart.entity.User;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserRepository userRepo;
	
	//in this class all method having pass user name so 
				//create a method and return name for all
	//common data
	@ModelAttribute //its run automatically when run this class
	public void addCommondata(Model m,Principal principal)
	{
		//user email in help of principal
		String UserName = principal.getName();
				
		//print the username(mail)
		System.out.println("UserName is :: "+UserName);//this is the main key of the each user
				
		//use UserRepository and collect all data of (User table) like name,id,mail,about,password,image,role
		User user = userRepo.getUserByUserName(UserName);
		System.out.println("user is :: "+user);
				
		//print all user data in web page transfer userdata
		m.addAttribute("userdata",user);
		
		//user name is
		System.out.println("user name is :: "+user.getName());
	}
	
	//home-dashboard
	@RequestMapping("/index")
	public String dashboard(Model model,Principal principal)
	{
		model.addAttribute("title","UserHome");
		return "User/user_dashboard";   //re-direct file to User-->user_dashboard
	}//method
	
	
	//addcontact handler
	@GetMapping("/addcontact")
	public String AddContact(Model model)
	{
		model.addAttribute("title","AddContact");
		model.addAttribute("contact",new Contact());
		
		return "User/addContact";
	}//method
	
	@PostMapping("/addContactDetails")
	public String insertContact(@ModelAttribute Contact contact, //Contact.class matching with addContact.html file carry with that name="" value
								  Principal principal)//user details collect
	
	{
		
	try {
		//user mail
		String mail=principal.getName();
		System.out.println("User mail id is :: "+mail);
		
		//user hole details
		User user=userRepo.getUserByUserName(mail);
		
		//add child fast
		contact.setUser(user);
		
		/* fast load child  them collect the contact and add the poticular contact (list)*/
		
		//add to parent to child
		user.getContact().add(contact);
		
		//save the obj help of parent 
		userRepo.save(user);
		
		
		System.out.println("Added to data base");
		
		//check the contact
		System.out.println("contact data :: "+contact);
		
	
	}//try
	
	catch (Exception e) {
	
		System.out.println("Error msg is "+e.getMessage());
		e.printStackTrace();
	}
	
	return "User/addContact";
	
	}//method
	
	
	
}//class
