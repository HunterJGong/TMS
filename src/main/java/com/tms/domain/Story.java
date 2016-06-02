package com.tms.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="tms_story")
public class Story {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="st_id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="st_sprint")
	private Sprint sprint;

	@OneToOne
	@JoinColumn(name="st_status")
	private Status status;
	
	@Column(name="st_name")
	private String name;
	
	@Column(name="st_description")
	private String description;
	
	@Column(name="st_points")
	private int points;
	
	@Column(name="st_end")
	private Timestamp end;

	public Story(){
	}

	public Timestamp getEnd(){
		return end;
	}
	public void setEnd(Timestamp end){
		this.end=end;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Sprint getSprint() {
		return sprint;
	}

	public void setSprint(Sprint sprint) {
		this.sprint = sprint;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}
	
	public Status getStatus(){
		return status;
	}
	public void setStatus(Status s){
		this.status=s;
	}

	public void setDone(){
		this.id=5;
	}
	
	@Override
	public String toString() {
		return "Story [id=" + id + ", sprint=" + sprint + ", name=" + name + ", description=" + description
				+ ", points=" + points + "]";
	}
	
}
