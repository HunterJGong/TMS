package com.tms.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tms.domain.Sprint;
import com.tms.repository.SprintRepository;

@Service
@Transactional
public class SprintService {
	@Autowired
	private SprintRepository repo;
	
	public ArrayList<Sprint> getAllSprints(){
		return (ArrayList<Sprint>) repo.findAll();
	}
	public Sprint getSprintById(Integer id){
		return repo.getSprintById(id);
	}
	public ArrayList<Sprint> getSprintByProject(Integer id){
		return (ArrayList<Sprint>) repo.getSprintsByProject(id);
	}
	public Sprint save(Sprint sprint){
		return repo.save(sprint);
	}
}
