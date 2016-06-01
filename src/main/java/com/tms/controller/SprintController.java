package com.tms.controller;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
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
import com.tms.domain.Sprint;
import com.tms.domain.Status;
import com.tms.domain.Story;
import com.tms.domain.Task;
import com.tms.service.SprintService;
import com.tms.service.StatusService;
import com.tms.service.StoryService;
import com.tms.service.TaskService;

@Controller
public class SprintController{
	
	@Autowired
	SprintService sprintService;
	@Autowired
	StoryService storyService;
	@Autowired
	TaskService taskService;
	@Autowired
	StatusService statusService;
	
	@RequestMapping(value={"manager/sprint","developer/sprint"},method=RequestMethod.GET)
	public String getSprint(HttpServletRequest request){
		HttpSession session = request.getSession();
		int sprint_id = 0;
		try{
			if(request.getParameter("id") != null){
				sprint_id = Integer.parseInt(request.getParameter("id"));
				session.setAttribute("lcSprint", sprint_id);
			}else{
				sprint_id =	(int) session.getAttribute("lcSprint");
			}
			session.setAttribute("sprint_id", sprint_id);
			request.getSession().setAttribute("sprint_id", sprint_id);
		}catch(NumberFormatException e){
			sprint_id = (Integer) session.getAttribute("sprint_id");
		}		
		
		
		Sprint sprint = sprintService.getSprintById(sprint_id);
		List<Story> stories = storyService.getBySprintId(sprint.getId());
		
		request.setAttribute("sprint", sprint);
		request.setAttribute("stories", stories);
		return "sprint";
	}	

	@RequestMapping(value="/manager/sprint/createStory")
	public String createStory(HttpServletRequest request){
		// Validate story
		ArrayList<String> errors = new ArrayList<>();
		String name = request.getParameter("name"); //validate name
		if(name==null){
			return "redirect:/manager/sprint";
		}
		
		if(name.length()==0){
			errors.add("Name is Required");
		}		
		String description = request.getParameter("description"); //validate description
		if(description.length()==0){
			errors.add("Description is Required");
		}
		String points_string = request.getParameter("points");
		Integer points = 0;
		if(points_string.length()==0){
			errors.add("Points Field Required");
		}else if(points_string.length()>10){
			errors.add("Points Field Too Large");
		}else{
			try{
				points = Integer.parseInt(points_string);
				if(points<=0){
					errors.add("Points Must be Positive");
				}
			}catch(NumberFormatException nfe){
				errors.add("Not a Valid Number");
			}
		}
		
		Integer sprint_id = (Integer) request.getSession().getAttribute("sprint_id");
		request.setAttribute("errors", errors);
		
		if(errors.size()==0){
			Story story = new Story();
			Sprint sprint = new Sprint();
			
			story.setName(name);
			story.setPoints(points);
			story.setDescription(description);
			story.setStatus(new Status(1));
			
			
			sprint.setId(sprint_id);
			story.setSprint(sprint);
			
			storyService.save(story);
		}
			
		return getSprint(request);
	}
	
	//BURN
	@RequestMapping(value="/sprint/burnData")
	public @ResponseBody String burnData(HttpServletRequest request){
		Gson gson = new Gson();
		Integer sprint_id = (Integer) request.getSession().getAttribute("sprint_id");
		List<Story> allStories = storyService.findBySprintIdOrderByEndAsc(sprint_id);
		
		Sprint sprint = sprintService.getSprintById(sprint_id);
		Timestamp start = sprint.getStart();
		int current_day = getCurrentDayIndex(start)+1;
		int total = 0;
		int[] daily = new int[current_day+1];
		
		for(Story s : allStories){
			total += s.getPoints();
		}
		for(int i=0; i<current_day; i+=1){
			daily[i]=total;
		}
		
		
		ArrayList<Integer> newDaily = new ArrayList<>();
		newDaily.add(total);
		
		for(Story s : allStories){
			if(s.getEnd() != null){
				int dayIndex = getDayIndex(start, s.getEnd());
				
				for(int j=dayIndex; j<current_day; j+=1){
					daily[j] -= s.getPoints();
					//System.out.println("dayIndex:"+dayIndex+" minus:"+s.getPoints());
				}
			}
		}
		
		int daily_length = daily.length;
		if(daily.length>11){
			daily_length=11;
		}
		
		//System.out.println("TOTAL:"+total);
		for(int i=0; i<daily_length; i+=1){
			newDaily.add(daily[i]);
			//System.out.println("daily:"+daily[i]);
		}
		//System.out.println(newDaily);
		return gson.toJson(newDaily);
	}
	//END BURN
	
	//LANE
	@RequestMapping(value="/sprint/laneData")
	public @ResponseBody String laneData(HttpServletRequest request){
		Gson gson = new Gson();
		HashMap<String, Double> map = new HashMap<>();
		
		int total_tasks=0;
		int sprint_id = (int) request.getSession().getAttribute("sprint_id");
		List<Status> statuses = statusService.findAll();
		for(Status s : statuses){
			map.put(s.getStatus(), 0.0);
		}		
		// SUM TASKS. COUNT TASK BY TYPE.
		List<Story> allStories = storyService.findBySprintIdOrderByEndAsc(sprint_id);
		for(Story s : allStories){
			List<Task> tasks = taskService.findByStoryId(s.getId());
			for(Task t : tasks){
				total_tasks += 1;
				String status = t.getStatus().getStatus();
				map.put(status, map.get(status)+1.0);
				System.out.println("STAT"+t.getStatus());
			}
		}
		// CONVERT COUNT TO PERCENT
		for(Status s : statuses){
			double current = map.get(s.getStatus());
			current /= total_tasks;
			current *= 100.0;
			map.put(s.getStatus(), current);
		}
		//FORMAT FOR HIGHCHARTS
		ArrayList<String[]> response = new ArrayList<>();
		for(Status s : statuses){
			String[] str = new String[2];
			str[0] = s.getStatus();
			str[1] = ""+map.get(s.getStatus());
			response.add(str);
		}
		return gson.toJson(response);
	}
	// END LANE
	
	private int getCurrentDayIndex(Timestamp start){
		long now_ms = System.currentTimeMillis();
		long diff_ms = now_ms - start.getTime();
		int result = (int) (diff_ms / (1000*60*60*24));
		if(result>10){
			result=10;
		}
		return result;
	}
	private int getDayIndex(Timestamp start, Timestamp end){
		long diff_ms = end.getTime()-start.getTime();
		int diff_days = (int) (diff_ms / (1000*60*60*24));
		if(Math.abs(diff_days)>10){
			return 10;
		}else{
			return diff_days;
		}
	}

}