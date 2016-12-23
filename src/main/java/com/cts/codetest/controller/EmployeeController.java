package com.cts.codetest.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cts.codetest.model.Employee;
import com.cts.codetest.model.Response;

@Controller
public class EmployeeController {
	
	@RequestMapping(value="/emp", method=RequestMethod.POST, consumes={MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE}, produces={MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public Response addUpdateEmpInfo(@RequestBody Employee employee){
		System.out.println(employee);
		Response response= new Response("101","sdfsf"); 
		return response;
	}
}
