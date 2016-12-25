package com.cts.codetest.exception;

public class EmpException extends RuntimeException{
	
	private static final long serialVersionUID = -3367827015959506341L;
	
	private String errorCodeKey;
	private String errorMessageKey;
	
	public EmpException() {
	}
	public EmpException(String errorCodeKey, String errorMessageKey) {
		super();
		this.errorCodeKey = errorCodeKey;
		this.errorMessageKey = errorMessageKey;
	}
	
	public EmpException(String errorCodeKey, String errorMessageKey, Throwable throwable) {
		super(throwable);
		this.errorCodeKey = errorCodeKey;
		this.errorMessageKey = errorMessageKey;
	}
	
	@Override
	public String toString() {
		return "EmpException [errorCodeKey=" + errorCodeKey + ", errorMessageKey=" + errorMessageKey + "]";
	}
	public String getErrorCodeKey() {
		return errorCodeKey;
	}
	public void setErrorCodeKey(String errorCodeKey) {
		this.errorCodeKey = errorCodeKey;
	}
	public String getErrorMessageKey() {
		return errorMessageKey;
	}
	public void setErrorMessageKey(String errorMessageKey) {
		this.errorMessageKey = errorMessageKey;
	}	
}
