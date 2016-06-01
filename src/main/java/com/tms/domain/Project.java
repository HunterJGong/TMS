package com.tms.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;


@Entity
@Table(name="tms_project")
public class Project {
	
	@Id
	@Column(name="p_id")
	private int id;
	
	@Column(name="p_name")
	private String name;
	
	@Column(name="p_start")
	private Timestamp start;
	
	@Column(name="p_end_projected")
	private Timestamp endProjected;
	
	@Column(name="p_end_actual")
	private Timestamp endActual;
	
	public Project(){
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Timestamp getStart() {
		return start;
	}

	public void setStart(Timestamp start) {
		this.start = start;
	}

	public Timestamp getEndProjected() {
		return endProjected;
	}

	public void setEndProjected(Timestamp endProjected) {
		this.endProjected = endProjected;
	}

	public Timestamp getEndActual() {
		return endActual;
	}

	public void setEndActual(Timestamp endActual) {
		this.endActual = endActual;
	}

	@Override
	public String toString() {
		return "Project [id=" + id + ", name=" + name + ", start=" + start + ", endProjected=" + endProjected
				+ ", endActual=" + endActual + "]";
	}
	
}
