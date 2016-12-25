package com.cts.codetest.controller;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cts.codetest.model.Employee;
import com.cts.codetest.model.Response;
import com.cts.codetest.service.EmployeeService;

@RestController
public class EmployeeController extends ExceptionController{
	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	@Qualifier(value = "employeeService")
	private EmployeeService employeeService;
	
	
	
	@RequestMapping(value = "/emp", method = RequestMethod.POST, consumes = {
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_XML_VALUE })
	public Response addUpdateEmpInfo(@RequestBody Employee employee, HttpServletResponse httpResponse) {
		Long startTime = System.currentTimeMillis();

		Response response = employeeService.insertUpdateEmployee(employee);
		Long timeTaken = System.currentTimeMillis() - startTime;

		httpResponse.addHeader("X-Time-Taken", String.valueOf(timeTaken));
		LOGGER.info("Time Taken-> {}", httpResponse.getHeaders("X-Time-Taken"));
		return response;
	}	
}
