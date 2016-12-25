package com.cts.codetest.exception;

public class ResourceNotFoundException  extends EmpException{

	public ResourceNotFoundException() {
	}
	
	public ResourceNotFoundException(String errorCodeKey, String errorMessageKey) {
		super(errorCodeKey,errorMessageKey);
	}
	public ResourceNotFoundException(String errorCodeKey, String errorMessageKey, Throwable throwable) {
		super(errorCodeKey,errorMessageKey,throwable);
	}
}
