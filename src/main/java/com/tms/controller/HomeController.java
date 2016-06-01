package com.tms.controller;

import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tms.domain.Project;
import com.tms.domain.User;
import com.tms.service.ProjectService;

@Controller
public class HomeController {

	@Autowired
	ProjectService projectServ;
	
	@RequestMapping(value="home")
	public String getLogin2(){
		System.out.println("login successful");
		return "home";
	}
	
	@RequestMapping(value="developer")
	public String developerLogin(HttpServletRequest request){
		HttpSession sess = request.getSession();
		User user = (User)sess.getAttribute("user");
		Project myPro = null;
		if(user.getProject() != null){
			myPro = projectServ.getProjectById(user.getProject().getId());
		}
		sess.setAttribute("myPro", myPro);
		System.out.println("developer login successful");
		return "developer";
	}
	
	@RequestMapping(value="manager")
	public String managerLogin(HttpServletRequest request){
		HttpSession sess = request.getSession();
		ArrayList<Project> proList = projectServ.getAllProjects();
		sess.setAttribute("projects", proList);
		System.out.println("manager login successful");
		return "manager";
	}
	
	@RequestMapping(value="/manager/createProject")
	public String createStory(HttpServletRequest request){
		// Validate project
		ArrayList<String> errors = new ArrayList<>();
		String name = request.getParameter("name"); //validate name
		
		if(name == null || name.length()==0){
			errors.add("Name is Required");
		}
		
		String date = request.getParameter("date");
		
		request.setAttribute("errors", errors);
		
		if(errors.size()==0){
			Project proj = new Project();
			
			proj.setName(name);
			proj.setEndProjected(Timestamp.valueOf(date + " 00:00:00"));
			
			projectServ.save(proj);
		}
			
		return managerLogin(request);
	}
}
