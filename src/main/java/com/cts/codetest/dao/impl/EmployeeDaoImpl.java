package com.cts.codetest.dao.impl;

import static com.cts.codetest.util.Constants.ERROR_CODE_SERVER_ERROR;
import static com.cts.codetest.util.Constants.ERROR_MSG_SERVER_ERROR;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cts.codetest.dao.EmployeeDao;
import com.cts.codetest.exception.InternalServerException;
import com.cts.codetest.model.Employee;

@Repository(value = "employeeDao")
public class EmployeeDaoImpl implements EmployeeDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeDaoImpl.class);
	@Value("${db.emp.upsert.stmt}")
	private String insertUpdateStmt;

	@Value("${db.emp.qiesry.isEditable}")
	private String validateEmployee;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public boolean isEmployeeInformationEditableOrInsertable(Employee employee) {

		try {
			List<Boolean> resultList = jdbcTemplate.query(validateEmployee, (rs, index) -> rs.getBoolean("IS_EDITABLE"),
					employee.getEmpNo());
			return (resultList == null || resultList.isEmpty() || resultList.get(0)) ? true : false;
		} catch (Exception exp) {
			throw new InternalServerException(ERROR_CODE_SERVER_ERROR, ERROR_MSG_SERVER_ERROR, exp);
		}
	}

	@Override
	public boolean addUpdateEmployeeInformation(Employee employee) {

		try {
			LOGGER.debug("Payload" + employee);
			int updateCount = jdbcTemplate.update(insertUpdateStmt, new Object[] { employee.getEmpNo(),
					employee.getEmpName(), employee.getJoiningDate(), employee.getDepartment() });

			if (updateCount > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception exp) {
			throw new InternalServerException(ERROR_CODE_SERVER_ERROR, ERROR_MSG_SERVER_ERROR, exp);
		}
	}
}
