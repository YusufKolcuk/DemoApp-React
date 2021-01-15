package com.xperta.pdf;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.xperta.entity.City;
import com.xperta.service.CitiesService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRAbstractBeanDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class PDFService{
	
	@Autowired
	public CitiesService citiesService;
	
	public void jasperReport(String jasperReport,HttpServletResponse response) throws JRException, IOException {
		// first get all cities
		List<City> cities = citiesService.getAllCities();
		
		// create datasource
		JRAbstractBeanDataSource dataSource = new JRBeanCollectionDataSource(cities);
		
		// compiling report file
		File file = ResourceUtils.getFile("classpath:jasper/cities.jrxml");
		JasperReport jasReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		
		// setting report params
		Map<String,Object> param = new HashMap<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd_HHmmss"); //yyyy-MM-dd HH:mm:ss
		String now = sdf.format( new Date() );
		param.put("WeatherForecast", now);
		
		// generating pdf report
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasReport, param,dataSource);
		if(jasperReport.equalsIgnoreCase("pdf")) {
			response.addHeader("Content-disposition", "attachment; filename=report.pdf");			
			ServletOutputStream servletOutputStream=response.getOutputStream();  
			   JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);  
			   servletOutputStream.flush();
			   servletOutputStream.close(); 
		}
	}
}
