package com.ata.joblist.exception;

public class InvalidParamException extends RuntimeException{
	
	public InvalidParamException(String message) {
		super(message);
	}
	
	public InvalidParamException(String message, Throwable throwable) {
		super(message, throwable);
	}

}
