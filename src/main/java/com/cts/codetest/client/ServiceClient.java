package com.cts.codetest.client;

import java.net.URI;
import java.util.Date;

import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.cts.codetest.model.Employee;
import com.cts.codetest.model.Response;

public class ServiceClient {

	public static void main(String[] args) throws Exception{
	    RestTemplate restTemplate = new RestTemplate();
        Employee employee = new Employee();
        employee.setEmpNo(1111);
        employee.setEmpName("Test Name");
        employee.setDepartment("IT");
        employee.setJoiningDate(new Date());
        
        RequestEntity<Employee> requestEntity = new RequestEntity<Employee>( employee , HttpMethod.POST, new URI("http://localhost:8080/emp"));
        
        
        
        requestEntity.getHeaders().add("Accept", "application/xml");
        requestEntity.getHeaders().add("Content-Type","application/xml");
        
	    ResponseEntity<Response> response = restTemplate.postForEntity("http://localhost:8080/emp", requestEntity, Response.class);
	    
	    System.out.println("response: "+response);
	     
	}
}
