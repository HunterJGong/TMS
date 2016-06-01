package com.tms.domain;

public class TaskForm {
	private int status;
	private int user;
	private int task;
	
	public TaskForm(){}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getUser() {
		return user;
	}

	public void setUser(int user) {
		this.user = user;
	}

	public int getTask() {
		return task;
	}

	public void setTask(int task) {
		this.task = task;
	}

	@Override
	public String toString() {
		return "TaskForm [status=" + status + ", user=" + user + ", task=" + task + "]";
	}
	
}
