package com.tms.controller;
import com.google.gson.Gson;
import com.tms.domain.Role;
import com.tms.domain.User;
import com.tms.domain.UserForm;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tms.service.UserService;

@Controller
@RequestMapping(value="/ajax")
public class AjaxController {
	
	@Autowired
	private UserService userService;
	
	// Get User by ID and send it back as JSON
	@RequestMapping(value="profile", method=RequestMethod.GET)
	public @ResponseBody String get(@RequestParam int id, HttpServletRequest request){
		Gson gson = new Gson();
		User user = new User();
		try{
			User current_user = (User) request.getSession().getAttribute("user");
			if(current_user.getId()==id){
				user = userService.getUserById(id);
			}
		}catch(Exception e){
		}
		return gson.toJson(user);
	}

	// Save a User then send it back as JSON
	// TODO check if user is manager first
	@RequestMapping(value="profile/new")
	public @ResponseBody String post(@RequestBody String data, HttpServletRequest request){
		HttpSession session = request.getSession();
		Gson gson = new Gson();
		try{
			UserForm temp_user = gson.fromJson(data, UserForm.class);
			User current_user = (User) session.getAttribute("user");
			
			if(current_user.getRole().getId()==2){
				User response = new User();
				if(temp_user.validate()){
					User user = new User();
					user.setUsername(temp_user.getUsername());
					user.setPassword(temp_user.getPassword());
					user.setEmail(temp_user.getEmail());
					user.setPhone(temp_user.getPhone());
					user.setBio(temp_user.getBio());
					user.setImage(temp_user.getImage());
					user.setRole(new Role(1));
					response = userService.save(user);
				}
				response.setMessages(temp_user.getMessages());
				return gson.toJson(response);
			}else{
				// Do nothing if not a manager
				return gson.toJson(new User());
			}
		}catch(Exception e){
			// Do nothing if not logged in
			return gson.toJson(new User());
		}
	}
	
	// Update a user then send it back as JSON
	@RequestMapping(value="profile/update")
	public @ResponseBody String put(@RequestBody String data, HttpServletRequest request){
		HttpSession session = request.getSession();
		Gson gson = new Gson();
		
		try{
			UserForm temp_user = gson.fromJson(data, UserForm.class);
			User current_user = (User) session.getAttribute("user");
			
			if(temp_user.getId() == current_user.getId()){
				User response = new User();
				if(temp_user.validate()){
					User old_user = userService.getUserById(temp_user.getId());
					old_user.setUsername(temp_user.getUsername());
					old_user.setPassword(temp_user.getPassword());
					old_user.setEmail(temp_user.getEmail());
					old_user.setPhone(temp_user.getPhone());
					old_user.setBio(temp_user.getBio());
					old_user.setImage(temp_user.getImage());
					response = userService.save(old_user);
					
					session.setAttribute("user", response);
				}
				response.setMessages(temp_user.getMessages());
				return gson.toJson(response);
			}else{
				//Attemping to edit someone else returns a blank User
				return gson.toJson(new User());
			}
		}catch(Exception e){
			return gson.toJson(new User());
		}
		
	}	
	
}
