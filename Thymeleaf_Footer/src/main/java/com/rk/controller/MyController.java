package com.rk.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyController {
	
	@GetMapping("/footerTag")
	public String footerServiceUsed(Model m)
	{
	
		return "service";
	}//method
	
}//class
