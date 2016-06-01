package com.tms.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tms_sprint")
public class Sprint {
	
	@Id
	@Column(name="sp_id")
	private int id;
	
	@Column(name="sp_project")
	private int project;
	
	@Column(name="sp_start")
	private Timestamp start;
	
	@Column(name="sp_end")
	private Timestamp end;

	public Sprint(){
		
	}
	
	public Sprint(int id, int project, Timestamp start, Timestamp end) {
		super();
		this.id = id;
		this.project = project;
		this.start = start;
		this.end = end;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProject() {
		return project;
	}

	public void setProject(int project) {
		this.project = project;
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
		return "Sprint [id=" + id + ", project=" + project + ", start=" + start + ", end=" + end + "]";
	}
}
