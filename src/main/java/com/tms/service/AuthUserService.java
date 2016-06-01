package com.tms.service;

import org.springframework.stereotype.Component;

import com.tms.domain.User;

@Component
public class AuthUserService {
	
	//Authenicate User at Login
	public boolean auth(User user, String password){
		boolean authUser = false;
			
		if(password.equals(user.getPassword())){
			authUser = true;
		}else {
			authUser = false;
		}
			
		return authUser;
	}
}
