package com.xperta.quartz.jobs;


import java.net.URLEncoder;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xperta.entity.City;
import com.xperta.service.CitiesService;


/**
 * INFO: because of API Problem at Weather Data Service (api.openweathermap.org) temporarily this job disabled!
 * 
 * @author yusuf
 *
 */

@Component("weatherDataService")
public class WeatherDataService
{
	@Autowired
	CitiesService citiesService;

	public void refreshWeatherData() {
		String urlParameters=null;
		try {
			urlParameters = "fName=" + URLEncoder.encode("???", "UTF-8") + "&lName=" + URLEncoder.encode("???", "UTF-8");
			ArrayList<City> id_value=citiesService.getAllId();
			Object[] key_obj=id_value.toArray();
			for (Object push : key_obj) {
				WeatharDataJob.executePost("http://api.openweathermap.org/data/2.5/group?id="+push+"&appid=294fcf03ced531801723f6d093726c4c&units=metric", urlParameters);
				
				City city=new City();
				city.setName(JsonParser.name);
				city.setId((long)JsonParser.id);
				city.setHumidity((double)JsonParser.humidity);
				city.setTemp((double)JsonParser.temp);
				city.setTempMax((double)JsonParser.temp_max);
				city.setTempMin((double)JsonParser.temp_min);
				city.setPressure((double)JsonParser.pressure);
				city.setLat((double)JsonParser.lat);
				city.setLon((double)JsonParser.lon);
				city.setDesc(JsonParser.description);
				citiesService.update(city);
			}			
	    } catch (Exception e) {
			System.out.println("ERROR: getting data from webservice!!" + e.getMessage()); // TODO: use logger!! 
		}
    }
}