package com.xperta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xperta.dao.UserDAO;
import com.xperta.entity.User;
import com.xperta.entity.UserRole;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserDAO userDAO;

	public Long insert(User user) {
		
		Long success = userDAO.insert(user);
		if (success>0) {
			user.getId(); // TODO:check user id if its correct!
			
			UserRole userRole =  new UserRole();
			userRole.setUsr_id( user.getId());
			userRole.setRole_id(1l);
			userDAO.save(userRole);
		}
		return userDAO.insert(user);
	}

	public void update(User user) {
		userDAO.update(user);
	}

	public User getFindByUsername(String username) {
		return userDAO.getFindByUsername(username);
	}
	
	public Boolean isAdmin(User user) {
		return userDAO.checkAdmin(user);
	}
}
