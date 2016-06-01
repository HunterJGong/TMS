package com.tms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tms.domain.Project;
import com.tms.domain.Role;
import com.tms.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	public User getUserById(Integer id);
	public List<User> findAll();
	public User getUserByUsername(String username);
	public List<User> getUsersByProject(Project id);
	public List<User> getUsersByRole(Role role);

	@SuppressWarnings("unchecked")
	public User save(User user);

}
