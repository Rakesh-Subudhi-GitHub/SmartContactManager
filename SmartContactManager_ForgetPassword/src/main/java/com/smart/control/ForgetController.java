package com.smart.control;

import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.smart.dao.UserRepository;
import com.smart.entity.User;
import com.smart.service.EmailService;

@Controller
public class ForgetController {

	//Otp 
	Random random = new Random(1000);
	
	//mail send 
	@Autowired
	private EmailService emailService;
	
	//user
	@Autowired
	private UserRepository userRepo;
	
	//change password so need BCryptPasswordEncoder to convert
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
/*==========================================================================================================*/
	
	//email id from open handler
	@RequestMapping("/forgot")
	public String openEmailFrom() {
		
		return "forgot_email_form";
	}//method
	
	
	
	
	//forget p
	@PostMapping("/send-otp")
	public String sendOTP(@RequestParam("email") String email,HttpSession session) 
	{
		System.out.println("Email is :::: "+email);
		
		//generating otp of 4 digit
		
		int otp=random.nextInt(99999);
		
		System.out.println("OTP is ::: "+otp);
		
		//Write code for send Otp to Email
		
		String subject="OTP from SmartContactManager";
		String message=""
				+"<div style = 'border:1px solid #e2e2e2; padding:20px' >"
				+"<h1 text-center>"
				+"OTP is : "
				+"<b>"+otp
				+"</n>"
				+"</h1>"
				+"</div>";
				
				
		String to=email;
		
		//calling Service class to send mail
		boolean flag = emailService.sendEmail(subject, message, to);
		
		if(flag)
		{
			
			//otp store in db create a coloumn and simple store that column permanetly if you chnage then it change 
			   //or store  session its some time it store then it remove its better  
			
			//i am store in session
			session.setAttribute("myotp",otp);
			session.setAttribute("email", email);
			
			return "verify_otp";
			
		}
		
		else {
		
			session.setAttribute("message","Check your mail id !!");
			
			return "forgot_email_form";
		}
		
		
	}//method
	
	//verify otp handler
	@PostMapping("/verify-otp")
	public String verifyOtp(@RequestParam("userotp") int userotp, //catch the otp user entry
										HttpSession session) //Session otp send in previous method so collect that
	{
		
		int myOtp=(int) session.getAttribute("myotp");//collect the otp 
		String email=(String) session.getAttribute("email");//user mail
		
		System.out.println("session otp is ::: "+myOtp);
		System.out.println("user type otp is::: "+userotp);
		
		//user otp convertion
		//int otp=Integer.parseInt(userotp);
		
		if(myOtp==userotp)
		{
			System.out.println("otp is match and change the password:::");
			
			System.out.println("user mail id is :: "+email);
			
			//change the password
			User user = userRepo.getUserByUserName(email);
			
			System.out.println(user);
			
			//check the user
			if(user==null)
			{
				//user is not present 
				session.setAttribute("message","User does not exits with this email !!");//user mail is not present
				return "forgot_email_form";
			
			}
			
			
		else {
				
			//its means that user exist or change the password easily
			
			return "password_change_form";
			}
		}//if
		
		else
		{
			System.out.println("password is not match:::");
			
			
			//same verify_otp with error message
			
			session.setAttribute("message"," You have entered wrong OTP ");
		    return "verify_otp";
		}
		
	}//method
	
	//change the password
	@PostMapping("/change-password")
	public String changePassword(@RequestParam("newPass") String newPassword,HttpSession session)
	{
		//fetch the session and get the email
		String email=(String) session.getAttribute("email");
		
		User user = userRepo.getUserByUserName(email); //collect the user
		
		//chanage the password and encode that
		user.setPassword(bCryptPasswordEncoder.encode(newPassword));
		
		//save the password
		userRepo.save(user);  //bcz change the password help of user so save the user automatically update the password
		
		//show the message to update the password
		
		session.setAttribute("message"," Your password change successfully !!! ");
	    return "redirect:/signin";//redirect the page to login page
	}
	
}//class
