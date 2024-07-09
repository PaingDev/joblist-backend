package com.ata.joblist.core;

import java.util.HashMap;
import java.util.Map;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.ata.joblist.exception.InvalidParamException;

@ControllerAdvice
public class GlobalExceptionHandler  {

	@ExceptionHandler(BadRequestException.class)
	ResponseEntity<?> handleBadRequestException(BadRequestException ex) {
		String bodyOfResponse = ex.getMessage();
		System.out.println("BadRequestException");
		return new ResponseEntity<>(bodyOfResponse, HttpStatus.BAD_REQUEST);//handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler(InvalidParamException.class)
	ResponseEntity<Object> handleInvalidParamException(InvalidParamException ex) {
		Map<String, Object> body = new HashMap<>();
		body.put("message", ex.getMessage());
		body.put("status", HttpStatus.BAD_REQUEST.value());
		System.out.println("InvalidParamException");
		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	ResponseEntity<Object> handleException(Exception ex) {
		String bodyOfResponse = ex.getMessage();
		Map<String, Object> body = new HashMap<>();
		body.put("message", ex.getMessage());
		body.put("status", HttpStatus.BAD_REQUEST.value());
		ex.printStackTrace();
		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
		//handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR,request);
	}

}
