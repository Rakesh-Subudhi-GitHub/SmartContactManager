package com.smart.control;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.rk.helper.Message;
import com.smart.dao.UserRepository;
import com.smart.entity.User;

@Controller
public class SmartController {

	@Autowired
	UserRepository userRepo;
	
	@GetMapping("/home")
	public String homeExecute(Model model)
	{
		model.addAttribute("title","Home-SmarterManager");
		return "home";

	}//method
	
	@GetMapping("/about")
	public String aboutExecute(Model model)
	{
		model.addAttribute("title","About-SmarterManager");
		return "about";

	}//method
	
	@GetMapping("/contact")
	public String contactExecute(Model model)
	{
		model.addAttribute("title","Contact-SmarterManager");
		return "contact";

	}//method
	
	@GetMapping("/signUp")
	public String RegisterExecute(Model model)
	{
		model.addAttribute("title","Register-SmarterManager");
		model.addAttribute("user",new User());
		return "registerPage";

	}//method
	
	//handle for registering user
	//@RequestMapping(value="",method =RequestMethod.POST)
	@PostMapping("/do_register")
	public String registerUser(@ModelAttribute("user") User user,// Register form data take it and store User class
									@RequestParam(value="agreement",defaultValue = "false")boolean agreement,//only check box doesnot match so need this
									                                                                    Model model,//model data store
									                                                                    	HttpSession session)//show the error msg
	{
		try {
			if(!agreement)
			{
				System.out.println("you have not agreed the term and conditions");
				
				//throw catch block directly
				throw new Exception("you have not agreed the term and conditions");
			}//if
			
			user.setRole("ROLE_USER");
			user.setEnable(true);
			
			System.out.println("Agreemet "+agreement);
			System.out.println("User data "+user);
			
			//add user data
			User result = userRepo.save(user);
			
			model.addAttribute("user",new User());
			
			//print result
			session.setAttribute("message",new Message("User Successfully Registered...","alert-success"));//helper class message class and pass those value
			
			return "registerPage";
			
		}//try 
		catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("user",user);
			session.setAttribute("message",new Message("Something want wrong!! "+e.getMessage(),"alert-danger"));//helper class message class and pass those value
		
			return "registerPage";
		}//catch

	}//method
	
}//class
