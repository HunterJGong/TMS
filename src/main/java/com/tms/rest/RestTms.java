package com.tms.rest;

import java.util.ArrayList;

import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tms.domain.Project;
import com.tms.service.ProjectService;

@RestController
@RequestMapping("rest/tmsStats/")
public class RestTms {

	@Autowired
	private ProjectService projectService;
	
	@RequestMapping(value="totalProjects", method=RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE})
	public ArrayList<Project> countProjects(){
		System.out.println("totalProjects");
		ArrayList<Project> projects = projectService.getAllProjects();
		
		GenericEntity<ArrayList<Project>> parent = new GenericEntity<ArrayList<Project>>(projects) {};
		
		//return Response.status(Response.Status.OK).entity(parent).build();
		return projectService.getAllProjects();
	}
	
	
	@RequestMapping(value="openProjects", method=RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE})
	public ArrayList<Project> openProjects(){
		System.out.println("openProjects");
		ArrayList<Project> projects = projectService.getOpenProjects();
		System.out.println(projects);
		
		GenericEntity<ArrayList<Project>> parent = new GenericEntity<ArrayList<Project>>(projects) {};
		
		//return Response.status(Response.Status.OK).entity(parent).build();
		return projectService.getOpenProjects();
	}
	
	@RequestMapping(value="completedProjects", method=RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE})
	public ArrayList<Project> completedProjects(){
		System.out.println("openProjects");
		ArrayList<Project> projects = projectService.getCompletedProjects();
		System.out.println(projects);
		
		GenericEntity<ArrayList<Project>> parent = new GenericEntity<ArrayList<Project>>(projects) {};
		
		//return Response.status(Response.Status.OK).entity(parent).build();
		return projectService.getCompletedProjects();
	}
	
	@RequestMapping(value="{projectName}", method=RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE})
	public Project findProject(@PathVariable String projectName){
		System.out.println("openProjects");
		Project project = projectService.getProjectByName(projectName);
		System.out.println(project);
		
		//GenericEntity<ArrayList<Project>> parent = new GenericEntity<ArrayList<Project>>(projects) {};
		
		//return Response.status(Response.Status.OK).entity(project).build();
		return projectService.getProjectByName(projectName);
	}
	
	
}
