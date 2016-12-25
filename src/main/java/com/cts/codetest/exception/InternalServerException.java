package com.cts.codetest.exception;

public class InternalServerException extends EmpException{

	public InternalServerException() {
	}
	
	public InternalServerException(String errorCodeKey, String errorMessageKey) {
		super(errorCodeKey,errorMessageKey);
	}
	public InternalServerException(String errorCodeKey, String errorMessageKey, Throwable throwable) {
		super(errorCodeKey,errorMessageKey,throwable);
	}
}
