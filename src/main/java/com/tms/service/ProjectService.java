package com.tms.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tms.domain.Project;
import com.tms.repository.ProjectRepository;

@Service
@Transactional
public class ProjectService {
	
	@Autowired
	private ProjectRepository projectRepo;
	
	public ArrayList<Project> getAllProjects(){
		return (ArrayList<Project>)projectRepo.findAll();
	}
	
	public ArrayList<Project> getOpenProjects(){
		return projectRepo.findAllByEndActualNull();
	}
	
	public ArrayList<Project> getCompletedProjects(){
		return projectRepo.findAllByEndActualNotNull();
	}
	
	public Project getProjectByName(String name){
		return projectRepo.findByName(name);
	}
	
	public Project getProjectById(Integer id){
		return projectRepo.getProjectById(id);
	}
	
	public Project save(Project proj){
		return projectRepo.save(proj);
	}
}
