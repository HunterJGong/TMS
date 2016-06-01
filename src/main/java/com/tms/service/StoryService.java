package com.tms.service;

import java.util.List;
import com.tms.domain.Story;

public interface StoryService {
	public List<Story> findAll();
	public Story getStoryById(Integer id);
	public Story save(Story story);
	public List<Story> getBySprintId(int id);
	public List<Story> findBySprintIdOrderByEndAsc(Integer sprint_id);
}
