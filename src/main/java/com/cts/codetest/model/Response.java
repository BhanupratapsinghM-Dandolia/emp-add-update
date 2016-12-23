package com.cts.codetest.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="response")
public class Response {

	private String responseCode;
	private String responseMessage;

	public Response() {
	}
	public Response(String responseCode, String responseMessage) {
		super();
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
	}
	
	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	public String getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
}
