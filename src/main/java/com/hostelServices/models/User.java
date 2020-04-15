package com.hostelServices.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity

@NamedNativeQuery(
	    name="LoginQuery",
	    query="select * from user_Details where mail_id =? and password =?",
	    resultClass=User.class
	)

@Table(name= "UserDetails")
@JsonInclude(Include.NON_NULL)
public class User {

@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE)
private Long userId;

@NotEmpty(message="Please enter username")
@Column(unique=true)
private String userName;
@Email
@NotEmpty(message="Please enter mailId")
private String mailId;


@Size(max=10,message="Invalid PhoneNumber")
@NotEmpty(message="Please enter phoneNumber")
private String phoneNumber;

@NotEmpty(message="Please enter password")
@JsonBackReference
private String password;

User(){
	
}
@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
private List<Hostel> hostelList;


public Long getUserId() {
	return userId;
}
public void setUserId(Long userId) {
	this.userId = userId;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public String getMailId() {
	return mailId;
}
public void setMailId(String mailId) {
	this.mailId = mailId;
}
public String getPhoneNumber() {
	return phoneNumber;
}
public void setPhoneNumber(String phoneNumber) {
	this.phoneNumber = phoneNumber;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public List<Hostel> getHostelList() {
	return hostelList;
}
public void setHostelList(List<Hostel> hostelList) {
	this.hostelList = hostelList;
}

}
