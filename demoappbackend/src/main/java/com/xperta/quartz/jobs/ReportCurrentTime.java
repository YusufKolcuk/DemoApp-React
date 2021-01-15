package com.xperta.quartz.jobs;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xperta.entity.City;
import com.xperta.service.CitiesService;

@Component("reportCurrentTime")
public class ReportCurrentTime
{
	@Autowired
	CitiesService citiesService;
	

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat(
            "HH:mm:ss");
    
    public void printCurrentTime()
    {
    	String urlParameters=null;
		try {
			urlParameters = "fName=" + URLEncoder.encode("???", "UTF-8") +
			"&lName=" + URLEncoder.encode("???", "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<City> id_value=citiesService.getAllId();
		Object[] key_obj=id_value.toArray();
		for (Object push : key_obj) {
			WeatharDataJob.executePost("http://api.openweathermap.org/data/2.5/group?id="+push+"&appid=294fcf03ced531801723f6d093726c4c&units=metric", urlParameters);
			City city=new City();
			//System.out.println("deneme123"+JsonParser.name);
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
		
        System.out.println("Current time = " + dateFormat.format(new Date()));
    }

}