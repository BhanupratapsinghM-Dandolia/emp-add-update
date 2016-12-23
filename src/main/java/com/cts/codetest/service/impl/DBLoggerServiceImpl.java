package com.cts.codetest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cts.codetest.dao.DBLoggerDao;
import com.cts.codetest.service.DBLoggerService;

@Service(value="dbLoggerService")
public class DBLoggerServiceImpl implements DBLoggerService {

	@Autowired
	@Qualifier(value="dbLoggerDao")
	private DBLoggerDao dbLoggerDao;
	
	@Override
	public void log(String logType, String activity, String message, String detail) {
		dbLoggerDao.log(logType, activity, message, detail);
	}
}
