package com.xperta.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "persons")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull(message = "{demoapp.validation.username.NotNull.message}")
	private String username;
	@NotNull(message = "{demoapp.validation.displayname.NotNull.message}")
	private String displayname;
	@NotNull(message = "{demoapp.validation.email.NotNull.message}")
	private String email;
	@NotNull(message = "{demoapp.validation.password.NotNull.message}")
	private String password;
	
	private Boolean isAdmin;
	
	public Boolean getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDisplayname() {
		return displayname;
	}

	public void setDisplayname(String displayname) {
		this.displayname = displayname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", displayname=" + displayname + ", email=" + email
				+ ", password=" + password + ", isAdmin=" + isAdmin + "]";
	}

	

}
