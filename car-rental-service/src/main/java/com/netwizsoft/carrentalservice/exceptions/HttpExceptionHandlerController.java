package com.netwizsoft.carrentalservice.exceptions;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.netwizsoft.carrentalservice.model.ErrorMessage;


@ControllerAdvice
public class HttpExceptionHandlerController {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorMessage> handleNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
		var errors = methodArgumentNotValidException.getAllErrors();
		
		ErrorMessage message = null;
		
		if (errors != null && !errors.isEmpty()) {
			message = new ErrorMessage(400, errors.get(0).getDefaultMessage());
			return new ResponseEntity<ErrorMessage>(message, HttpStatus.BAD_REQUEST);
		}
		
		message = new ErrorMessage(400, "Bad Request");
		return new ResponseEntity<ErrorMessage>(message, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler({NoSuchElementException.class, NumberFormatException.class})
	public ResponseEntity<ErrorMessage> handleNoSuchElementException(Exception exception) {
		ErrorMessage message = new ErrorMessage(404, "Not Found");
		return new ResponseEntity<ErrorMessage>(message, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler({HttpMessageNotReadableException.class, HttpRequestMethodNotSupportedException.class})
	public ResponseEntity<ErrorMessage> handleNotReadableException(Exception exception) {
		ErrorMessage message = new ErrorMessage(400, "Bad Request");
		return new ResponseEntity<ErrorMessage>(message, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ErrorMessage> handleIllegalException(IllegalArgumentException exception) {
		ErrorMessage message = new ErrorMessage(400, exception.getMessage());
		return new ResponseEntity<ErrorMessage>(message, HttpStatus.BAD_REQUEST);
	}
}
