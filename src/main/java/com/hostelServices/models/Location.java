package com.hostelServices.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
public class Location {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int locId;
	@NotEmpty(message="Please enter address details")
	private String address;
	@NotEmpty(message="Please enter latitude details")
	private double latitude;
	@NotEmpty(message="Please enter longitude details")
	private double longitude;
	
	private String landmark;
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public String getLandmark() {
		return landmark;
	}
	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}
	
}
