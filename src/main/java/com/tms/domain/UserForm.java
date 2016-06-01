package com.tms.domain;

import java.util.ArrayList;
import java.util.List;

public class UserForm {
	private Integer id;
	private String username;
	private String password;
	private String password2;
	private String email;
	private String bio;
	private String phone;
	private String image;
	private List<String> messages;
	
	public boolean validate(){
		ArrayList<String> toReturn = new ArrayList<>();
		if(getUsername().equals("")){
			toReturn.add("Username is Required");
		}else if(getUsername().length()<4 || getUsername().length()>20){
			toReturn.add("Username must be 4-20 Characters");
		}
		
		if(getPassword().equals("") || getPassword2().equals("")){
			toReturn.add("Password Fields are Required");
		}else if(!getPassword().equals(getPassword2())){
			toReturn.add("Password Fields do not match");
		}
		
		if(getEmail().equals("")){
			toReturn.add("Email is Required");}
		messages=toReturn;
		return toReturn.size()==0;
	}
	
	public UserForm(){}

	public String getUsername() {
		if(username==null){
			return "";
		}else{
			return username;
		}
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		if(password==null){
			return "";
		}else{
			return password;
		}
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword2() {
		if(password2==null){
			return "";
		}else{
			return password2;
		}
	}
	public void setPassword2(String password) {
		this.password2 = password;
	}	
	

	public String getEmail() {
		if(email == null){
			return "";
		}else{
			return email;
		}
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getBio() {
		if(bio==null){
			return "";
		}else{
			return bio;
		}
	}
	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getPhone() {
		if(phone == null){
			return "";
		}else{
			return phone;
		}
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getImage() {
		if(image==null){
			return "";
		}else{
			return image;
		}
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	public Integer getId(){
		return id;
	}
	public void setId(Integer id){
		this.id=id;
	}
	

	public List<String> getMessages(){
		if(messages == null){
			return new ArrayList<String>();
		}else{
			return messages;
		}
	}
	public void setMessages(List<String> messages){
		this.messages=messages;
	}	
	
	@Override
	public String toString() {
		return "UserPublic [username=" + username + ", password=" + password + ", email=" + email + ", bio=" + bio
				+ ", phone=" + phone + ", image=" + image + "]";
	}
	
}
