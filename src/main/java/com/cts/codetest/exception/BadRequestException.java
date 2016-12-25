package com.cts.codetest.exception;

public class BadRequestException extends EmpException{

	public BadRequestException() {
	}
	
	public BadRequestException(String errorCodeKey, String errorMessageKey) {
		super(errorCodeKey,errorMessageKey);
	}
	public BadRequestException(String errorCodeKey, String errorMessageKey, Throwable throwable) {
		super(errorCodeKey,errorMessageKey,throwable);
	}
}
