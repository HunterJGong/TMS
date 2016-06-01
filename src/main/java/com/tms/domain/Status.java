package com.tms.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tms_status")
public class Status {
	
	public Status(){}
	public Status(int id){
		this.id=id;
	}
	
	@Id
	@Column(name="ts_id")
	private int id;
	
	@Column(name="ts_status")
	private String status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void markDone(){
		this.id=5;
		this.status="Done";
	}
	public boolean isDone(){
		return this.id==5;
	}
	
	@Override
	public String toString() {
		return "Status [id=" + id + ", status=" + status + "]";
	}
	
}
