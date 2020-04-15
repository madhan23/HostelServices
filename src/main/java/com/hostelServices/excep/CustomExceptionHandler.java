package com.hostelServices.excep;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;



@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorResponse> handleAllExceptions(Exception ex, WebRequest request) {
		ErrorResponse error = new ErrorResponse(new Timestamp(System.currentTimeMillis()),ex.getMessage(),"Failure");
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
		ErrorResponse error = new ErrorResponse(new Timestamp(System.currentTimeMillis()),ex.getMessage(),"Failure");
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(HostelNotFoundException.class)
	public final ResponseEntity<ErrorResponse> handleHostelNotFoundException(HostelNotFoundException ex, WebRequest request) {
		ErrorResponse error = new ErrorResponse(new Timestamp(System.currentTimeMillis()),ex.getMessage(),"Failure");
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.NOT_FOUND);
	}

	@Override
	protected final ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> errorDetails = new ArrayList<>();
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			errorDetails.add(error.getDefaultMessage());
		}
		ErrorResponse error = new ErrorResponse(new Timestamp(System.currentTimeMillis()),"Failure",errorDetails);
		return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
	}

}
