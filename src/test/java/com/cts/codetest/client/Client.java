package com.cts.codetest.client;

import java.util.Date;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.cts.codetest.model.Employee;
import com.cts.codetest.model.Response;

public class Client {

	public static void main(String[] args) {
		RestTemplate restTemplate = new RestTemplate();
        Employee employee = new Employee();
        employee.setEmpNo(21101);
        employee.setEmpName("Test Name");
        employee.setDepartment("IT");
        employee.setJoiningDate(new Date());
        
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_XML);
        
        HttpEntity<Employee> requestEntity = new HttpEntity<Employee>(employee, requestHeaders);
        
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        
	    ResponseEntity<Response> responseEntity = restTemplate.exchange("http://localhost:8080/emp", HttpMethod.POST, requestEntity, Response.class);
	    		
	    Response response = responseEntity.getBody();
	    
	    System.out.println("HTTP Status Code: "+responseEntity.getStatusCode());
	    System.out.println("Response Code: "+response.getResponseCode());
	    System.out.println("Response Message: "+response.getResponseMessage());
	}
}
