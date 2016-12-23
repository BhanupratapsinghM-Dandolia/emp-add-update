package com.cts.codetest.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cts.codetest.dao.EmployeeDao;
import com.cts.codetest.model.Employee;

@Repository(value="employeeDao")
public class EmployeeDaoImpl implements EmployeeDao{

	@Value("${db.emp.upsert.stmt}")
	private String insertUpdateStmt;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public boolean addUpdateEmployeeInformation(Employee employee) {

		int updateCount = jdbcTemplate.update(
			    insertUpdateStmt,
			    new Object[]{employee.getEmpNo(), employee.getEmpName(), employee.getJoiningDate(), employee.getDepartment()}
			);
		
		if(updateCount>0){
			return true;
		} else {
			return false;
		}
	}
}
