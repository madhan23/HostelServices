
package com.hostelServices.excep;

import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ErrorResponse {
	private Timestamp timestamp;
	private String message;
	private String status;
	private List<String> ErrorDetails;


	public ErrorResponse(Timestamp timestamp,String status, List<String> ErrorDetails) {
		super();
		this.timestamp = timestamp;
		this.status = status;
		this.ErrorDetails = ErrorDetails;
	}

	
	public ErrorResponse(Timestamp timestamp, String message, String status) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.status = status;
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getErrorDetails() {
		return ErrorDetails;
	}

	public void setErrorDetails(List<String> details) {
		this.ErrorDetails = details;
	}

}
