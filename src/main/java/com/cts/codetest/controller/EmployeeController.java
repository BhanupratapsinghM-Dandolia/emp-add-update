package com.cts.codetest.controller;

import java.io.StringWriter;

import org.codehaus.plexus.util.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cts.codetest.model.Employee;
import com.cts.codetest.model.Response;
import com.cts.codetest.service.DBLoggerService;
import com.cts.codetest.service.EmployeeService;
import com.cts.codetest.util.Constants;

@RestController
public class EmployeeController {

	@Autowired
	@Qualifier(value = "employeeService")
	private EmployeeService employeeService;

	@Autowired
	@Qualifier(value = "dbLoggerService")
	private DBLoggerService dbLoggerService;
	
	@RequestMapping(value = "/emp", method = RequestMethod.POST,
			consumes = { MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_XML_VALUE })
	public Response addUpdateEmpInfo(@RequestBody Employee employee ) {
		return employeeService.insertUpdateEmployee(employee);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	Response handleXMLException(HttpMessageNotReadableException ex) {
		
		Response response = new Response();
		response.setResponseCode(Constants.ERROR_CODE_INVALID_XML);
		response.setResponseMessage(Constants.ERROR_MSG_INVALID_XML);

		dbLoggerService.log(Constants.LOG_TYPE_ERROR, "Request validation", Constants.ERROR_CODE_INVALID_XML + ":" + Constants.ERROR_MSG_INVALID_XML, ExceptionUtils.getFullStackTrace(ex));
		return response;
	}
}
