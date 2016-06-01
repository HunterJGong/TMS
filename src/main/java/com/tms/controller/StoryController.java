package com.tms.controller;

import java.sql.Timestamp;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.tms.domain.Status;
import com.tms.domain.Story;
import com.tms.domain.Task;
import com.tms.domain.TaskForm;
import com.tms.domain.User;
import com.tms.service.StatusService;
import com.tms.service.StoryService;
import com.tms.service.TaskService;
import com.tms.service.UserService;

@Controller
public class StoryController {
	
	@Autowired
	private TaskService taskService;
	@Autowired
	private StatusService statusService;
	@Autowired
	private UserService userService;
	@Autowired
	private StoryService storyService;
	
	@RequestMapping(value={"manager/story","developer/story"},method=RequestMethod.GET)
	public String getStories(HttpServletRequest request){
		HttpSession session = request.getSession();
		int story_id = 0;
		try{
			if(request.getParameter("id") != null){
				story_id = Integer.parseInt(request.getParameter("id"));
				session.setAttribute("lcStory",story_id);
			}else{
				story_id = (int) session.getAttribute("lcStory");
			}
			session.setAttribute("story_id", story_id);
			request.getSession().setAttribute("story_id", story_id);
		}catch(NumberFormatException e){
			story_id = (Integer) session.getAttribute("story_id");
		}
		
		Story story = storyService.getStoryById(story_id);
		
		List<Task> tasks = taskService.findByStoryId(story.getId());
		List<Status> statuses = statusService.findAll();
		User current_user = (User) request.getSession().getAttribute("user");
		if(current_user.getRole().getId()==1){
			for(int i=0; i<statuses.size(); i+=1){
				Status s = statuses.get(i);
				if(s.getStatus().equalsIgnoreCase("done")){
					statuses.remove(i);
				}
			}
		}
		
		request.setAttribute("users", userService.findAll());
		request.setAttribute("statuses", statuses);
		request.setAttribute("tasks", tasks);
		request.setAttribute("story", story);
		
		if(current_user.getRole().getId()==1){
			return "storydeveloper";
		}
		else{
			return "storymanager";
		}
	}
	
	@RequestMapping(value="story/updateTask")
	public @ResponseBody String updateTask(@RequestBody String data){
		Gson gson = new Gson();
		TaskForm input = gson.fromJson(data, TaskForm.class);

		Task task = taskService.findById(input.getTask());
		Status status = null;
		if(input.getStatus()>0){
			status = statusService.findById(input.getStatus());
		}
		
		if(task.getUser()==null){
			User user = userService.getUserById(input.getUser());
			task.setUser(user);
		}
		task.setStatus(status);
		if(input.getStatus() == 5){
			task.setEnd(new Timestamp(System.currentTimeMillis()));
		}
		return gson.toJson(taskService.save(task));
	}
	
	@RequestMapping(value="story/grabTask")
	public @ResponseBody String grabTask(@RequestBody String data, HttpServletRequest request){
		Gson gson = new Gson();
		TaskForm input = gson.fromJson(data, TaskForm.class);
		Task task = taskService.findById(input.getTask());
		
		User user = (User) request.getSession().getAttribute("user");
		task.setUser(user);
		task.setStatus(new Status(1));
		return gson.toJson(taskService.save(task));
	}
	
//	@RequestMapping(value="story/verify")
//	public @ResponseBody String verifyTask(@RequestBody String data, HttpServletRequest request){
//		Story story = new Story();
//		Gson gson = new Gson();
//		User current_user = (User) request.getSession().getAttribute("user");
//		if(current_user.getRole().getId()==2){
////			TaskForm input = gson.fromJson(data, TaskForm.class);
////			task = taskService.findById(input.getTask());
////			task.getStatus().markDone();
////			taskSer
//		}
//		return gson.toJson(story);
//	}
	
	@RequestMapping(value="/manager/story/createTask")
	public String createTask(HttpServletRequest request){
		int id = 0;
		try{
			id = Integer.parseInt(request.getParameter("id"));
		}catch(NumberFormatException e){
			return "redirect:/manager/story";
		}
		String description = request.getParameter("description");
		if(description.length() > 0){
			Story story = storyService.getStoryById(id);
			Task task = new Task();
			task.setDescription(description);
			task.setStory(story);
			task.setStatus(new Status(1));
			taskService.save(task);
			request.setAttribute("id", id);
		}else{
			request.setAttribute("task_message", "Please enter a Task Description");
		}
		return getStories(request);
	}
	
	@RequestMapping(value="/manager/story/complete")
	public String completeStory(HttpServletRequest request){
		int id = 0;
		try{
			id = Integer.parseInt(request.getParameter("id"));
		}catch(NumberFormatException e){
			return "redirect:/manager/story";
		}
		Story story = storyService.getStoryById(id);
		Status status = new Status();
		status.markDone();
		story.setStatus(status);
		story.setEnd(new Timestamp(System.currentTimeMillis()));
		
		
		storyService.save(story);
		
		return getStories(request);
	}
}
