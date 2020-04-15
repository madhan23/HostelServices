package com.hostelServices.dto;

import java.io.Serializable;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ResponseDto implements Serializable {

	private static final long serialVersionUID = 177896656444L;

	private Timestamp timestamp;
	private String status;
	private Object data;
	private String token;
	private String message;

	public ResponseDto(Timestamp timestamp, String status, Object data) {
		super();
		this.timestamp = timestamp;
		this.status = status;
		this.data = data;
	}

	

	public ResponseDto(Timestamp timestamp, String status, String token, String message) {
		super();
		this.timestamp = timestamp;
		this.status = status;
		this.token = token;
		this.message = message;
	}



	public String getMessage() {
		return message;
	}



	public void setMessage(String message) {
		this.message = message;
	}



	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}


	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	
	

	

}
