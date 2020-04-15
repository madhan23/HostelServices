package com.hostelServices.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.UniqueElements;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name="Hostel")
@JsonInclude(Include.NON_NULL)
public class Hostel implements Serializable{
private static final long serialVersionUID = 16546546L;
@Id
@Column(name="HostelID")
@GeneratedValue(strategy = GenerationType.SEQUENCE)
private long hostelId;
@NotEmpty(message="Please enter hosteName details")
private String hosteName;
@NotEmpty(message="Please enter area details")
private String area;

@OneToOne(cascade=CascadeType.ALL)
private Location locationDetails;

@OneToMany(cascade={CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.PERSIST},mappedBy="hostel")
private List<RoomDetails> roomDetails;

@ElementCollection(fetch = FetchType.LAZY)
@CollectionTable(name = "HostelOtherAmenties",joinColumns = @JoinColumn(name = "HostelID"))
@Column(name = "otherAmenties")
@UniqueElements
@NotEmpty(message="Please enter otherAmenties details")
private List<String> otherAmenties;

@JsonIgnore
@ManyToOne(fetch=FetchType.LAZY,cascade={CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.PERSIST})
private User user;


Hostel(){
	
}
public Long getHostelId() {
	return hostelId;
}

public void setHostelId(Long hostelId) {
	this.hostelId = hostelId;
}

public String getHosteName() {
	return hosteName;
}

public void setHosteName(String hosteName) {
	this.hosteName = hosteName;
}

public String getArea() {
	return area;
}

public void setArea(String area) {
	this.area = area;
}

public Location getLocationDetails() {
	return locationDetails;
}

public void setLocationDetails(Location locationDetails) {
	this.locationDetails = locationDetails;
}

public List<RoomDetails> getRoomDetails() {
	return roomDetails;
}

public void setRoomDetails(List<RoomDetails> roomDetails) {
	this.roomDetails = roomDetails;
}

public List<String> getOtherAmenties() {
	return otherAmenties;
}

public void setOtherAmenties(List<String> otherAmenties) {
	this.otherAmenties = otherAmenties;
}

public User getUser() {
	return user;
}

public void setUser(User user) {
	this.user = user;
}
}
