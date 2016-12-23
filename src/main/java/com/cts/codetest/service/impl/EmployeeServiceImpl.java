package com.cts.codetest.service.impl;

import org.aspectj.apache.bcel.classfile.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cts.codetest.dao.EmployeeDao;
import com.cts.codetest.model.Employee;
import com.cts.codetest.model.Response;
import com.cts.codetest.service.EmployeeService;
import static com.cts.codetest.util.Constants.*;

@Service(value="employeeService")
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	@Qualifier(value="employeeDao")
	private EmployeeDao employeedao;
	
	@Override
	public Response insertUpdateEmployee(Employee employee) {
		Response response = new Response();
		
		try{
		if(employeedao.isEmployeeInformationEditableOrInsertable(employee)){
			
			if(employeedao.addUpdateEmployeeInformation(employee)){				

				response.setResponseCode(SUCCESS_CODE);
				response.setResponseMessage(SUCCESS_MESSAGE);
				} else {

					response.setResponseCode(ERROR_CODE_SERVER_ERROR);
					response.setResponseMessage(ERROR_MSG_SERVER_ERROR);
			}
		}
		else{
			response.setResponseCode(ERROR_CODE_NON_EDITABLE);
			response.setResponseMessage(ERROR_MSG_NON_EDITABLE);
		}
		} catch(Exception exp){
			exp.printStackTrace();
			response.setResponseCode(ERROR_CODE_SERVER_ERROR);
			response.setResponseMessage(ERROR_MSG_SERVER_ERROR);
			
		}
		
		return response;
	}
}
