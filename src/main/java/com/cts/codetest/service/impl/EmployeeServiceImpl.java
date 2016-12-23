package com.cts.codetest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cts.codetest.dao.EmployeeDao;
import com.cts.codetest.model.Employee;
import com.cts.codetest.model.Response;
import com.cts.codetest.service.EmployeeService;

@Service(value="employeeService")
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	@Qualifier(value="employeeDao")
	private EmployeeDao employeedao;
	
	@Override
	public Response insertUpdateEmployee(Employee employee) {
		Response response = new Response();
		if(employeedao.addUpdateEmployeeInformation(employee)){
			response.setResponseCode("12312");
			response.setResponseMessage("fddgdfg");
		} else {
			response.setResponseCode("1111");
			response.setResponseMessage("xfdfd");
		}
		return response;
	}
}
