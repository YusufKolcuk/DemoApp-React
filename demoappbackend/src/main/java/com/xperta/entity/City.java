package com.xperta.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cities")
public class City {

	@Id
	private Long id;
	private String name;
	private Double temp;
	private Double tempMax;
	private Double tempMin;
	private Double humidity;
	private Double pressure;
	private Double lat;
	private Double lon;
	private String descr;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getTemp() {
		return temp;
	}

	public void setTemp(Double temp) {
		this.temp = temp;
	}

	public Double getTempMax() {
		return tempMax;
	}

	public void setTempMax(Double tempMax) {
		this.tempMax = tempMax;
	}

	public Double getTempMin() {
		return tempMin;
	}

	public void setTempMin(Double tempMin) {
		this.tempMin = tempMin;
	}

	public Double getHumidity() {
		return humidity;
	}

	public void setHumidity(Double humidity) {
		this.humidity = humidity;
	}

	public Double getPressure() {
		return pressure;
	}

	public void setPressure(Double pressure) {
		this.pressure = pressure;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLon() {
		return lon;
	}

	public void setLon(Double lon) {
		this.lon = lon;
	}

	public String getDesc() {
		return descr;
	}

	public void setDesc(String descr) {
		this.descr = descr;
	}

	@Override
	public String toString() {
		return "City [id=" + id + ", name=" + name + ", temp=" + temp + ", tempMax=" + tempMax + ", tempMin=" + tempMin
				+ ", humidity=" + humidity + ", pressure=" + pressure + ", lat=" + lat + ", lon=" + lon + ", descr="
				+ descr + "]";
	}
}
