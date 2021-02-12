package com.smart.control;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.rk.helper.Message;
import com.smart.dao.ContactRepository;
import com.smart.dao.UserRepository;
import com.smart.entity.Contact;
import com.smart.entity.User;

@Controller
@RequestMapping("/user")
public class UserController {

	//user parent repository
	@Autowired
	private UserRepository userRepo;
	
	//contact child repository
	@Autowired
	private ContactRepository contRepo;
	
	
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
	
	
	//add contact
	@PostMapping("/addContactDetails")
	public String insertContact(@ModelAttribute Contact contact, //Contact.class matching with addContact.html file carry with that name="" value
								    @RequestParam("profileimage") MultipartFile file, //image file save useing help of (MultipartFile)
								    				//Normally in addcontact.html image store name="profileimage" set it 
											Principal principal,//user details collect
												HttpSession session,//Success msg
													Model model)//model data
	{
		model.addAttribute("title","AddContact");
		
	try {
		
		//user mail
		String mail=principal.getName();
		System.out.println("User mail id is :: "+mail);
		
		//user hole details
		User user=userRepo.getUserByUserName(mail);
		
/*==========================================================================================================*/		
		
		//processing and uploading file...
		
		if(file.isEmpty())
		{
			//if file is empty then message
			System.out.println("image is empty:: ");
			
		}
		else
		{
			//upload the file to folder and upfdate the name to content
			contact.setImage(file.getOriginalFilename());//set the file name 
			
			File savefile = new ClassPathResource("static/userimg").getFile();
			
			
			//path of store file name or store  
			Path path = Paths.get(savefile.getAbsolutePath()+File.separator+file.getOriginalFilename());
			
			Files.copy(file.getInputStream(), //source file location
								path ,//Destination file location
								  StandardCopyOption.REPLACE_EXISTING );//store file thats way
		System.out.println("image is uploaded");
		
		}//else
		
		
		
		
/*---------------------------------------------------------------------------------------------------------*/

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
		
		//success all are added....
		session.setAttribute("message",new Message("Your contact is added !! add more..","success"));//helper class com.smart.helper(Message.class)
		
		//any error check then
			//throw new Exception("pass the message");
		
	}//try
	
	catch (Exception e) {
	
		System.out.println("Error msg is "+e.getMessage());
		e.printStackTrace();
		
		//else problem...
		session.setAttribute("message",new Message("Your contact is not added !! some internal problem..","danger"));//helper class com.smart.helper(Message.class)
		
	}
	
	return "User/addContact";
	
	}//method
	
	
	//show contacts handler 
	@GetMapping("/viewContact")
	public String showContacts(Model model,Principal principal)
	{
		model.addAttribute("title","View Contacts");

/*====================================  1st way   =========================================================*/		
	
		//fast collect the user mail help of principal
//		String email=principal.getName();
		
		//fetch user details collect 
//		User user=userRepo.getUserByUserName(email);
																	//its not good approaches
		//all contact get
//		List<Contact> contact = user.getContact();
		
//		System.out.println("Get all contact is :::: ");
//		contact.forEach(System.out::println);
		
		//transfer this data 
//		model.addAttribute("contact",contact);
		
/*===========================================================================================================*/		
	
		//find email help of principal
		String email = principal.getName();
		
		//fetch all details in User
		User user=userRepo.getUserByUserName(email);
		
		List<Contact> contact = contRepo.findContactByUser(user.getId());
		
		System.out.println("Contacts :: "+contact);
		
		model.addAttribute("contact",contact);
		
		return "User/showcontacts";
	}//method
	
	
	
	
	
}//class
