package com.tms.service;

import java.util.List;

import com.tms.domain.Project;
import com.tms.domain.Role;
import com.tms.domain.User;

public interface UserService {
	public User getUserById(Integer id);
	public User save(User user);
	public User getUserByUsername(String username);
	public List<User> findAll();
	public List<User> getUsersByProject(Project id);
	public List<User> getAllUsersByRole(Role role);
}
