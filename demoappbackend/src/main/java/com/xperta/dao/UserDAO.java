package com.xperta.dao;

import javax.persistence.Query;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xperta.entity.User;
import com.xperta.entity.UserRole;
import com.xperta.security.EncryptionHelper;

@Repository
public class UserDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	public Long insert(User user) {
		user.setPassword(EncryptionHelper.encrypt(user.getPassword()));
		return (Long) sessionFactory.getCurrentSession().save(user);
	}
	
	public void update(User user) {
		sessionFactory.getCurrentSession().update(user);
	}
	
	public Long save(UserRole userRole) {
		return (Long) sessionFactory.getCurrentSession().save(userRole);
	}
	
	public User getFindByUsername(String username) {
		Query query = sessionFactory.getCurrentSession().createQuery("FROM User WHERE username=:username").setString("username", username);
 		return ( query.getResultList().size() > 0 ) ? (User) query.getResultList().get(0) : null;
	}
	public Boolean checkAdmin(User user) {
		Query query = sessionFactory.getCurrentSession().createQuery("FROM UserRole WHERE usr_id=:usr_id").setLong("usr_id", user.getId());
		UserRole userRole = null;
		try {
			userRole = (UserRole) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (0==userRole.getRole_id());
	}
}
