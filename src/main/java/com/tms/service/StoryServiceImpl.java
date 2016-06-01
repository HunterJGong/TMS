package com.tms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tms.domain.Story;
import com.tms.repository.StoryRepository;

@Service
@Transactional
public class StoryServiceImpl implements StoryService{
	
	@Autowired
	private StoryRepository storyRepository;
	
	@Override
	public List<Story> findAll(){
		return storyRepository.findAll();
	}
	
	@Override
	public Story getStoryById(Integer id){
		return storyRepository.getStoryById(id);
	}
	
	@Override
	public Story save(Story story){
		return storyRepository.save(story);
	}

	@Override
	public List<Story> getBySprintId(int id) {
		return storyRepository.getBySprintId(id);
	}

	@Override
	public List<Story> findBySprintIdOrderByEndAsc(Integer sprint_id) {
		return storyRepository.findBySprintIdOrderByEndAsc(sprint_id);
	}
}
