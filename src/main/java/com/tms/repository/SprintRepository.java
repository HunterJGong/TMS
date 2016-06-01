package com.tms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tms.domain.Sprint;

@Repository
public interface SprintRepository extends JpaRepository<Sprint, Integer>{
	public List<Sprint> findAll();
	public Sprint getSprintById(Integer id);
	public List<Sprint> getSprintsByProject(Integer id);
	@SuppressWarnings("unchecked")
	public Sprint save(Sprint sprint);
}
