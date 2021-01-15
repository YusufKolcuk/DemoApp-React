package com.xperta.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author user
 *
 */
@Entity
@Table(name = "user_roles")
public class UserRole {
	@Id
	private Long usr_id;
	
	private Long role_id;

	public Long getUsr_id() {
		return usr_id;
	}

	public void setUsr_id(Long usr_id) {
		this.usr_id = usr_id;
	}

	public Long getRole_id() {
		return role_id;
	}

	public void setRole_id(Long role_id) {
		this.role_id = role_id;
	}
}