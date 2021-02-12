package com.rk.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MyController {

	@RequestMapping(value = "/about",method = RequestMethod.GET)
	public String about(Model model)
	{
		System.out.println("inside the about page working :::::::");
		
		model.addAttribute("name","Rakesh");      //dynamically send the data in about.html page
		
		model.addAttribute("currentDate",new Date().toString());
		
		return "about";
		
		//execute the about.html
		
	}//method
}//class
