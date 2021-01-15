package com.xperta.quartz.jobs;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonParser {
	public static String name,description;
	public static int id;
	public static Double temp_min,temp_max,temp,humidity,pressure,lat,lon;

	public static void WFJson(){
		Object response=WeatharDataJob.response;
		JSONObject myrespose=new JSONObject(response.toString());

	    JSONArray json_array = new JSONArray(myrespose.getJSONArray("list").toString());
	    for(int i=0;i<json_array.length();i++){
	    	JSONObject object = json_array.getJSONObject(i);
	    	id= object.getInt("id");
	    	name=object.getString("name");
	    	
	    	JSONObject coord_obj=object.getJSONObject("coord");
	    	lon = coord_obj.getDouble("lon");
	    	lat = coord_obj.getDouble("lat");
	    	
	    	JSONArray weather_ary=object.getJSONArray("weather");
	    	for (int j=0;j<1;j++) {
		    	JSONObject weatherObj = weather_ary.getJSONObject(j);
		    	description=weatherObj.getString("main");		    	
			}
	    	
	    	JSONObject main_obj	= object.getJSONObject("main");
	    	temp_min 			= main_obj.getDouble("temp_min");
	    	temp_max 			= main_obj.getDouble("temp_max");
	    	temp 				= main_obj.getDouble("temp");
	    	humidity 			= main_obj.getDouble("humidity");
	    	pressure 			= main_obj.getDouble("pressure");

	    }
	}
}
