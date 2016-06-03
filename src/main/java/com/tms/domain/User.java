package com.tms.domain;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


@Entity
@Table(name="tms_user")
public class User implements UserDetails {
	
	@Transient
	private User user;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="u_id")
	private int id;
	
	@OneToOne
	@JoinColumn(name="u_role")
	private Role role;
	
	@OneToOne
	@JoinColumn(name="u_project")
	private Project project;

	@Size(min=4, max=20)
	@NotEmpty(message="Please enter a username")
	@Column(name="u_username")
	private String username;
	
	@NotEmpty(message="Please enter a password")
	@Column(name="u_password")
	private String password;
	
	@Column(name="u_email")
	private String email;
	
	@Column(name="u_phone")
	private String phone;
	
	@Column(name="u_bio")
	private String bio;
	
	@Column(name="u_img_location")
	private String image;

	@Transient
	private List<String> messages;
	
	public User(){}
	public User(User user){
		this.user = user;
	}
	public User(User user, int id, Role role, Project project, String username, String password, String email,
			String phone, String bio, String image, List<String> messages) {
		super();
		this.user = user;
		this.id = id;
		this.role = role;
		this.project = project;
		this.username = username;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.bio = bio;
		this.image = image;
		this.messages = messages;
	}
	public User(User user, int id, Role role, Project project, String username, String password, String email,
			String phone, String bio, List<String> messages) {
		super();
		this.user = user;
		this.id = id;
		this.role = role;
		this.project = project;
		this.username = username;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.bio = bio;
		this.messages = messages;
	}

	public User(User user, int id, Role role, String username, String password, String email,
			String phone, String bio, String image, List<String> messages) {
		super();
		this.user = user;
		this.id = id;
		this.role = role;
		this.username = username;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.bio = bio;
		this.image = image;
		this.messages = messages;
	}
	
	public User(int id, Role role, Project project, String username, String password, String email,
			String phone, String bio, String image, List<String> messages) {
		super();
		this.id = id;
		this.role = role;
		this.project = project;
		this.username = username;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.bio = bio;
		this.image = image;
		this.messages = messages;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public List<String> getMessages(){
		return messages;
	}
	public void setMessages(List<String> messages){
		this.messages=messages;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", role=" + role + ", username=" + username + ", password=" + password + ", email="
				+ email + ", phone=" + phone + ", bio=" + bio + ", image=" + image + "]";
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		 final Set<GrantedAuthority> _grntdAuths = new HashSet<GrantedAuthority>();

	     List<Role> _roles = null;

	     if (user!=null) {
	             _roles = (List<Role>) user.getRole();
	     }

	     if (_roles!=null) {
	             for (Role _role : _roles) {
	                     _grntdAuths.add(new SimpleGrantedAuthority(_role.getRole()));
	             }
	     }

	     return _grntdAuths;
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.isAccountNonExpired();
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.isAccountNonLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.isCredentialsNonExpired();
	}

	@Override
	public boolean isEnabled() {
		return  this.isEnabled();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;
		return true;
	}
	

	
}
