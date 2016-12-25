package com.cts.codetest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cts.codetest.dao.EmployeeDao;
import com.cts.codetest.exception.InternalServerException;
import com.cts.codetest.model.Employee;
import com.cts.codetest.model.Response;
import com.cts.codetest.service.DBLoggerService;
import com.cts.codetest.service.EmployeeService;
import static com.cts.codetest.util.Constants.*;

@Service(value = "employeeService")
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	@Qualifier(value = "employeeDao")
	private EmployeeDao employeedao;

	@Autowired
	@Qualifier(value = "dbLoggerService")
	private DBLoggerService dbLoggerService;

	@Override
	public Response insertUpdateEmployee(Employee employee) {
		Response response = new Response();

			if (employeedao.isEmployeeInformationEditableOrInsertable(employee)) {

				if (employeedao.addUpdateEmployeeInformation(employee)) {

					response.setResponseCode(SUCCESS_CODE);
					response.setResponseMessage(SUCCESS_MESSAGE);
			
				} else {

					throw new InternalServerException(ERROR_CODE_SERVER_ERROR, ERROR_MSG_SERVER_ERROR);
				}
				
			} else {

				dbLoggerService.log(LOG_TYPE_ERROR, "Updating Employee Infomation",
						ERROR_CODE_NON_EDITABLE + ":" + ERROR_MSG_NON_EDITABLE, employee.toString());

				throw new InternalServerException(ERROR_CODE_NON_EDITABLE, ERROR_MSG_NON_EDITABLE);
			}
		return response;
	}
}
