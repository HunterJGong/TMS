package com.tms.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="tms_task")
public class Task {

	@Id
	@Column(name="t_id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="t_story")
	private Story story;
	
	@OneToOne
	@JoinColumn(name="t_user")
	private User user;
	
	@OneToOne
	@JoinColumn(name="t_status")
	private Status status;
	
	@Column(name="t_start")
	private Timestamp start;
	
	@Column(name="t_end")
	private Timestamp end;
	
	@Column(name="t_description")
	private String description;

	public String getDescription(){
		return description;
	}
	public void setDescription(String description){
		this.description=description;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Story getStory() {
		return story;
	}

	public void setStory(Story story) {
		this.story = story;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Timestamp getStart() {
		return start;
	}

	public void setStart(Timestamp start) {
		this.start = start;
	}

	public Timestamp getEnd() {
		return end;
	}

	public void setEnd(Timestamp end) {
		this.end = end;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", story=" + story + ", user=" + user + ", status=" + status + ", start=" + start
				+ ", end=" + end + "]";
	}
	
}
