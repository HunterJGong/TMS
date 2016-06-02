package com.tms.aspect;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class BreadCrumbAspect {

	@Autowired
	private ServletContext servletContext;
	private static String ctx;
	
	@PostConstruct
	private void setCtx(){
		ctx = servletContext.getContextPath();	
	}
	
	
	@Autowired(required=true)
	private HttpServletRequest request;
	
	@Before("execution(* com.tms.controller.HomeController.*(..))")
	public void updateHomeBreadCrumbs(JoinPoint joinPoint){
		HttpSession session = request.getSession();
		List<String> newBreadCrumbs = new ArrayList<>();
		String theRequest = request.getServletPath();
		if(theRequest.contains("developer")){
			newBreadCrumbs.add(ctx+"/developer");
		}else if (theRequest.contains("manager")){
			newBreadCrumbs.add(ctx+"/manager");
		}
		
		session.setAttribute("breadCrumbs",newBreadCrumbs);
		return;
	}
	
	@Before("execution(* com.tms.controller.ProjectController.*(..))")
	public void updateProjectBreadCrumbs(JoinPoint joinPoint){
		System.out.println("Aspect Controller");
		System.out.println(request.getServletPath());
		HttpSession session = request.getSession();
		//List<String> oldBreadCrumbs = (List<String>) session.getAttribute("breadCrumbs");
		List<String> newBreadCrumbs = new ArrayList<>();
		String theRequest = request.getServletPath();
		
		
		if(theRequest.contains("developer")){
			newBreadCrumbs.add(ctx+"/developer");
			newBreadCrumbs.add(ctx+"/developer/project");
			session.setAttribute("breadCrumbs", newBreadCrumbs);
			return;
		}else if (theRequest.contains("manager")){
			newBreadCrumbs.add(ctx+"/manager");
			newBreadCrumbs.add(ctx+"/manager/project");
			session.setAttribute("breadCrumbs", newBreadCrumbs);
			return;
		}
	}
	
	
	@Before("execution(* com.tms.controller.SprintController.get*(..))")
	public void updateSprintBreadCrumbs(JoinPoint joinPoint){
		System.out.println("Aspect Controller");
		System.out.println(request.getServletPath());
		
		HttpSession session = request.getSession();
		//List<String> oldBreadCrumbs = (List<String>) session.getAttribute("breadCrumbs");
		List<String> newBreadCrumbs = new ArrayList<>();
		String theRequest = request.getServletPath();
		
		
		if(theRequest.contains("developer")){
			newBreadCrumbs.add(ctx+"/developer");
			newBreadCrumbs.add(ctx+"/developer/project");
			newBreadCrumbs.add(ctx+"/developer/sprint");
			session.setAttribute("breadCrumbs", newBreadCrumbs);
			return;
		}else if (theRequest.contains("manager")){
			newBreadCrumbs.add(ctx+"/manager");
			newBreadCrumbs.add(ctx+"/manager/project");
			newBreadCrumbs.add(ctx+"/manager/sprint");
			session.setAttribute("breadCrumbs", newBreadCrumbs);
			return;
		}
		
	}
		
	@Before("execution(* com.tms.controller.StoryController.get*(..))")
	public void updateStoryBreadCrumbs(JoinPoint joinPoint){
		System.out.println("Aspect Controller");
		System.out.println(request.getServletPath());
		
		HttpSession session = request.getSession();
		//List<String> oldBreadCrumbs = (List<String>) session.getAttribute("breadCrumbs");
		List<String> newBreadCrumbs = new ArrayList<>();
		String theRequest = request.getServletPath();
		
		
		if(theRequest.contains("developer")){
			newBreadCrumbs.add(ctx+"/developer");
			newBreadCrumbs.add(ctx+"/developer/project");
			newBreadCrumbs.add(ctx+"/developer/sprint");
			newBreadCrumbs.add(ctx+"/developer/story");
			session.setAttribute("breadCrumbs", newBreadCrumbs);
			return;
		}else if (theRequest.contains("manager")){
			newBreadCrumbs.add(ctx+"/manager");
			newBreadCrumbs.add(ctx+"/manager/project");
			newBreadCrumbs.add(ctx+"/manager/sprint");
			newBreadCrumbs.add(ctx+"/manager/story");
			session.setAttribute("breadCrumbs", newBreadCrumbs);
			return;
		}
	}
}
