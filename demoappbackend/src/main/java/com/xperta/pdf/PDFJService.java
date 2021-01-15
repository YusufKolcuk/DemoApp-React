package com.xperta.pdf;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
public class PDFJService{
	@Autowired
	public CitiesService citiesService;
	
	public String jasperReport(String jasperReport,HttpServletResponse response) throws JRException, IOException {
		
		String path = System.getenv("temp");
		List<City> citys=citiesService.getAllCities();
		File file=ResourceUtils.getFile("classpath:jasper/cities.jrxml");
		JasperReport jasReport=JasperCompileManager.compileReport(file.getAbsolutePath());
		JRAbstractBeanDataSource dataSource = new JRBeanCollectionDataSource(citys);
		Map<String,Object> param=new HashMap<>();
		param.put("Weather Forecast", getRandomString());
		JasperPrint jasperPrint=JasperFillManager.fillReport(jasReport, param,dataSource);
		
		//TODO: pdf created under temp but not written to response ..
		if(jasperReport.equalsIgnoreCase("pdf")) {
			//JasperExportManager.exportReportToPdfFile(jasperPrint,path+"\\cities.pdf");
			//response.setContentType("application/x-download");
			response.addHeader("Content-disposition", "attachment; filename=report.pdf");			
			ServletOutputStream servletOutputStream=response.getOutputStream();  
			   JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);  
			   servletOutputStream.flush();
			   servletOutputStream.close(); 
		}
		return "report generated in path : " + path;
		
	}

	private String getRandomString() {
		// TODO Auto-generated method stub
		return UUID.randomUUID().toString();
	}


	

}
