package com.xperta.excel;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.xperta.entity.City;
import com.xperta.service.CitiesService;;

@Controller
public class ExcelController {
	
	@Autowired
	CitiesService citiesService;

	@RequestMapping(value = "/generate/excel.xls", method = RequestMethod.POST)
	ModelAndView generateExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Log.info("Calling generateExcel()...");
		List<City> cities = citiesService.getAllCities();
		ModelAndView modelAndView = new ModelAndView("excelView", "cities", cities);
		return modelAndView;
	}
}