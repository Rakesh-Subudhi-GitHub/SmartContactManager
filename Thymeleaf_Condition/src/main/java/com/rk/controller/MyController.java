package com.rk.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyController {
	
	@GetMapping("/cond")
	public String conditionChek(Model m)
	{
		System.out.println("Condition method work internal code execute:::::::::");
		
		m.addAttribute("isActive",false); //simple pass a boolean value  condition will be check
		
		m.addAttribute("gender","F"); //if unless check
		
		//switch case use
		List list = List.of(64,85,10,12,50,55,85,96,77);
		m.addAttribute("mylist",list);
		
		return "condition";
	}//method
	
	
}//class
