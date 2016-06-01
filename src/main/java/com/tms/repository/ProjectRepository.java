package com.tms.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tms.domain.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Integer> {

	public ArrayList<Project> findAll();
	public ArrayList<Project> findAllByEndActualNull();
	public ArrayList<Project> findAllByEndActualNotNull();
	public Project findByName(String projectName);
	public Project getProjectById(Integer id);
	@SuppressWarnings("unchecked")
	public Project save(Project proj);
}
