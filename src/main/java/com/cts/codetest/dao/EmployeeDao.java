package com.cts.codetest.dao;

import com.cts.codetest.model.Employee;

public interface EmployeeDao {

	boolean isEmployeeInformationEditableOrInsertable(Employee employee);
	
	boolean addUpdateEmployeeInformation(Employee employee);
}
