package com.tms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tms.domain.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task,Integer>{
	public List<Task> findByStoryId(Integer id);
	public Task findById(Integer id);
	
	@SuppressWarnings("unchecked")
	public Task save(Task task);
}
