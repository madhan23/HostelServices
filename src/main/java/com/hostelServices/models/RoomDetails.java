package com.hostelServices.models;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class RoomDetails implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 13334566L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long roomId;
	
	@NotEmpty(message="Please enter roomSharing details")
	private String roomSharing;
	@NotEmpty(message="Please enter roomType details")
	private String roomType;
	@NotEmpty(message="Please enter price details")
	private String price;
	
	@NotEmpty(message="Please enter isAvailable details")
	private boolean isAvailable;
	
	@NotEmpty(message="Please enter gender details")
	private String gender;

@ManyToOne(cascade={CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.PERSIST})
@JsonBackReference
private Hostel hostel;
	


	public Hostel getHostel() {
	return hostel;
}
public void setHostel(Hostel hostel) {
	this.hostel = hostel;
}
	public String getRoomSharing() {
		return roomSharing;
	}
	public void setRoomSharing(String roomSharing) {
		this.roomSharing = roomSharing;
	}
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	 }
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public boolean isAvailable() {
		return isAvailable;
	}
	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public long getRoomId() {
		return roomId;
	}
	public void setRoomId(long roomId) {
		this.roomId = roomId;
	}
}
