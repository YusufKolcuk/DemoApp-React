package com.xperta.excel;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.xperta.entity.City;;

public class ExcelView extends AbstractExcelView {
	@Override
	protected void buildExcelDocument(Map model, HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		List<City> cities = (List<City>) model.get("cities");

		HSSFSheet sheet = workbook.createSheet("Weather Forecast Report");
		 HSSFRow header = sheet.createRow(0);
		 header.createCell(0).setCellValue("Id");
		 header.createCell(1).setCellValue("Name");
		 header.createCell(2).setCellValue("Temp");
		 header.createCell(3).setCellValue("TempMax");
		 header.createCell(4).setCellValue("TempMin");
		 header.createCell(5).setCellValue("Humidity");
		 header.createCell(6).setCellValue("Pressure");

		 int counter = 1;
		 for (City city : cities) {
			HSSFRow row = sheet.createRow(counter++);
			row.createCell(0).setCellValue(city.getId());
			row.createCell(1).setCellValue(city.getName());
			row.createCell(2).setCellValue(city.getTemp());
			row.createCell(3).setCellValue(city.getTempMax());
			row.createCell(4).setCellValue(city.getTempMin());
			row.createCell(5).setCellValue(city.getHumidity());
			row.createCell(6).setCellValue(city.getPressure());
		 }	
	}
}