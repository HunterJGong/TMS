package com.tms.controller;

import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tms.domain.Project;
import com.tms.domain.Role;
import com.tms.domain.Sprint;
import com.tms.domain.User;
import com.tms.service.ProjectService;
import com.tms.service.SprintService;
import com.tms.service.UserService;

@Controller
public class ProjectController {
	@Autowired
	private SprintService sprintServ;
	
	@Autowired
	private ProjectService projectServ;
	
	@Autowired
	private UserService userServ;
	
	@RequestMapping(value={"manager/project","developer/project"}, method=RequestMethod.GET)
	public String getProjectPage(HttpServletRequest request){
		HttpSession sess = request.getSession();
		User currentUser = (User) sess.getAttribute("user");

		int currentProId = 0;
		try{
			currentProId = Integer.parseInt(request.getParameter("id"));
			sess.setAttribute("currentProId", currentProId);
		}catch(NumberFormatException e){
			currentProId = (Integer) sess.getAttribute("currentProId");
		}
		
		ArrayList<Sprint> sprList = sprintServ.getSprintByProject(currentProId);
		ArrayList<User> devList = (ArrayList<User>)userServ.getAllUsersByRole(new Role(1));
		
		Project currentPro = projectServ.getProjectById(currentProId);
		
		ArrayList<User> userList = (ArrayList<User>) userServ.getUsersByProject(currentPro);
		
		request.setAttribute("users", userList);
		request.setAttribute("devs", devList);
		request.setAttribute("project", currentPro);
		request.setAttribute("sprints", sprList);

		if(currentUser.getRole().getId()==1){
			return "projectDeveloper";
		}
		else{
			return "projectManager";
		}
	}
	
	@RequestMapping(value="/manager/project/createSprint")
	public String createSprint(HttpServletRequest request){
		Sprint spr = new Sprint();
		
		HttpSession sess = request.getSession();
		int currentProId = (int) sess.getAttribute("currentProId");
		
		spr.setProject(currentProId);
		spr.setStart(new Timestamp(System.currentTimeMillis()));
		
		sprintServ.save(spr);
			
		return getProjectPage(request);
	}
	
	@RequestMapping(value="/manager/project/assignDeveloper")
	public String assignDeveloper(HttpServletRequest request){
		HttpSession sess = request.getSession();
		Integer userId = Integer.parseInt(request.getParameter("dev"));
		User tempUser = userServ.getUserById(userId);
		Project tempPro = new Project();
		tempPro.setId((Integer)sess.getAttribute("currentProId"));
		tempUser.setProject(tempPro);

		userServ.save(tempUser);
		return getProjectPage(request);
	}
}
