package com.tms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tms.domain.Task;
import com.tms.repository.TaskRepository;

@Service
@Transactional
public class TaskService {
	
	@Autowired
	private TaskRepository taskRepository;
	
	public List<Task> findByStoryId(Integer id){
		return taskRepository.findByStoryId(id);
	}
	public Task findById(int id) {
		return taskRepository.findById(id);
	}
	public Task save(Task task){
		return taskRepository.save(task);
	}
	
}
