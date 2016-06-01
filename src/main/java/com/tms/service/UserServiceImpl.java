package com.tms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tms.domain.Project;
import com.tms.domain.Role;
import com.tms.domain.User;
import com.tms.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User getUserById(Integer id){
		return userRepository.getUserById(id);
	}
	@Override
	public User getUserByUsername(String username){
		return userRepository.getUserByUsername(username);
	}
	@Override
	public User save(User user){
		return userRepository.save(user);

	}
	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}
	@Override
	public List<User> getAllUsersByRole(Role role){
		return userRepository.getUsersByRole(role);
	}
	@Override
	public List<User> getUsersByProject(Project id){
		return userRepository.getUsersByProject(id);
	}
}
