package com.xperta.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xperta.entity.City;

@Repository
public class CitiesDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	public Long insert(City city) {
		return (Long) sessionFactory.getCurrentSession().save(city);
	}

	public void update(City city) {
		sessionFactory.getCurrentSession().update(city);
	}

	public void persist(City city) {
		sessionFactory.getCurrentSession().persist(city);
	}

	public void delete(City city_id) {
		sessionFactory.getCurrentSession().delete(city_id);
	}

	public City getfindId(Long id) {
		Query query = sessionFactory.getCurrentSession().createQuery("FROM City WHERE id=:id").setLong("id", id);
		return (City) query.getSingleResult();

	}

	public City addcityKey(Long id) {
		Query query = sessionFactory.getCurrentSession().createQuery("Ýnsert into City (id) values id=:id");
		return (City) query.getSingleResult();
	}

	public ArrayList<City> getAllId() {
		Query query = sessionFactory.getCurrentSession().createQuery("select id FROM City");

		return (ArrayList<City>) query.getResultList();
	}

	public ArrayList<City> getAllName() {
		Query query = sessionFactory.getCurrentSession().createQuery("select name FROM City");

		return (ArrayList<City>) query.getResultList();
	}

	public List<City> getAllCities() {
		Query query = sessionFactory.getCurrentSession().createQuery("FROM City");
		try {
			return query.getResultList();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
}
