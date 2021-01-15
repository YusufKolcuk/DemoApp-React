package com.xperta.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xperta.dao.CitiesDAO;
import com.xperta.entity.City;

/**
 * class to get weather data from an external page
 * 
 * @author xperta
 *
 */

@Service
@Transactional
public class CitiesService {
	
	@Autowired
	private CitiesDAO citiesDAO;

	@Transactional
	public Long create(City city) {
		return citiesDAO.insert(city);
	}

	@Transactional
	public void update(City city) {
		citiesDAO.update(city);
	}

	@Transactional
	public void persist(City city) {
		citiesDAO.persist(city);
	}

	@Transactional
	public Long delete(City city_id) {
		citiesDAO.delete(city_id);
		return 1l;
	}

	public City getCityById(Long id) {
		return citiesDAO.getfindId(id);
	}

	public ArrayList<City> getAllName() {
		return citiesDAO.getAllName();
	}

	public ArrayList<City> getAllId() {
		return citiesDAO.getAllId();
	}

	public List<City> getAllCities() {
		return citiesDAO.getAllCities();
	}
}
