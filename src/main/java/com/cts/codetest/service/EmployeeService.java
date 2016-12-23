package com.cts.codetest.service;

import com.cts.codetest.model.Employee;
import com.cts.codetest.model.Response;

public interface EmployeeService {

	Response insertUpdateEmployee(Employee employee);
}
