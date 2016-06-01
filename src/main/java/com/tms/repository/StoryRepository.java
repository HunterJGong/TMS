package com.tms.repository;

import com.tms.domain.Story;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface StoryRepository extends JpaRepository<Story, Integer>{
	public List<Story> findAll();
	public List<Story> getBySprintId(Integer id);
	public Story getStoryById(Integer id);
	@SuppressWarnings("unchecked")
	public Story save(Story story);
	public List<Story> findBySprintIdOrderByEndAsc(Integer sprint_id);
}
