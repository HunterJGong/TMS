package com.tms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tms.domain.Status;
import com.tms.repository.StatusRepository;

@Service
@Transactional
public class StatusService {
	
	@Autowired
	private StatusRepository statusRepository;
	
	public List<Status> findAll(){
		return statusRepository.findAll();
	}

	public Status findById(int id) {
		return statusRepository.findById(id);
	}
	
}
