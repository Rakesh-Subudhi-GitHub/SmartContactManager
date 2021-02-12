package com.rk.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyController {
	
	@GetMapping("/InharitePage")
	public String footerServiceUsed(Model m)
	{
	
		return "about";
	}//method
	
	@GetMapping("/InharitePage2")
	public String footerServiceUsed2(Model m)
	{
	
		return "contact";
	}//method
}//class
