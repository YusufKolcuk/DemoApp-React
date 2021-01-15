package com.xperta.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.xperta.entity.City;
import com.xperta.entity.User;
import com.xperta.error.ApiError;
import com.xperta.error.Log;
import com.xperta.pdf.PDFService;
import com.xperta.service.CitiesService;
import com.xperta.service.UserService;

import net.sf.jasperreports.engine.JRException;

/**
 * Reactjs-Spring MVC Project
 * 
 * @author user:Yusuf Kolcuk
 **/
@RestController
public class HomeController {

	@Autowired
	UserService userService;

	@Autowired
	PDFService pdfService;

	@Autowired
	CitiesService citiesService;
	


	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public void home() {
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(Model model, HttpServletRequest request) {
		return "redirect:/login/user";
	}

	@RequestMapping(value = "/showtable")
	public List<City> table(Model mdl, ModelAndView model, HttpServletRequest req) {
		List<City> cities = citiesService.getAllCities();
		model.addObject("cities", cities);
		model.setViewName("table");
		return cities;
	}

	// add city
	@RequestMapping(value = "table/add/{id}")
	public String RowAdd(@PathVariable("id") Long city_id) {
		City addCity = new City();
		addCity.setId(city_id);
		addCity.setName("Waiting");
		addCity.setDesc("Waiting");
		addCity.setLat(0.0);
		addCity.setLon(0.0);
		addCity.setTemp(0.0);
		addCity.setTempMax(0.0);
		addCity.setTempMin(0.0);
		addCity.setHumidity(0.0);
		addCity.setPressure(0.0);
		citiesService.create(addCity);
		Log.info("city added");
		return "redirect:/api/1.0/table";
	}

	// delete city
	@CrossOrigin
	@RequestMapping(value = "table/delete/{id}")
	public String RowDelete(@PathVariable("id") Long city_id) {
		City city = citiesService.getCityById(city_id);
		if (city != null) {
			citiesService.delete(city);
		}
		Log.info("city deleted");
		return "redirect:/table";
	}

	@RequestMapping(value = "/register/user", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createUser(@Valid @RequestBody User user, Model model, HttpServletRequest req) {
		Log.info(user.toString());
		userService.insert(user);
		return ResponseEntity.ok("user created");
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiError handleValidationException(MethodArgumentNotValidException exception) {
		ApiError error = new ApiError(400, "Validation error", "/register/user");
		Map<String, String> validationErrors = new HashMap<>();
		for(FieldError fieldError:exception.getBindingResult().getFieldErrors()) {
			validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		error.setValidationErrors(validationErrors);
		return error;
	}
	
	

	@RequestMapping(value = "/table/{format}", method = RequestMethod.POST)
	public String generatePdfJasper(@PathVariable String format, HttpServletRequest request,
			HttpServletResponse response) throws JRException, IOException {
		pdfService.jasperReport(format, response);
		return "redirect:/table";
	}
}
