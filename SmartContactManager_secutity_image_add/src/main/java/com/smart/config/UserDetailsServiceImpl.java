package com.smart.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.smart.dao.UserRepository;
import com.smart.entity.User;

public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		//fetching user from database
		User user = userRepo.getUserByUserName(username);//username is Email
		
		//check
		if(user==null)
		{
			throw new UsernameNotFoundException("could not found user ::");
		}
		
		CustomUserDetails customUserDetails=new CustomUserDetails(user);
		
		return customUserDetails;
	}//method

}//class
