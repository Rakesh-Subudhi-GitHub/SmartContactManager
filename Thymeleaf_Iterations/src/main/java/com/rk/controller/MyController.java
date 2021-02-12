package com.rk.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyController {
	
	//iteration example
	@GetMapping("/loop")
	public String iterationMethod(Model m)
	{
		
		//craete a list
		List<String> listnames = List.of("rakesh","rohit","laxmi","rohit","karan","devi");
		
		//send data to itreation.html
		m.addAttribute("names",listnames);
		
		return "iteration";
	}//method
	
}//class
