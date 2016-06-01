package com.tms.repository;

import com.tms.domain.Status;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Integer> {
	public List<Status> findAll();

	public Status findById(int id);
}
