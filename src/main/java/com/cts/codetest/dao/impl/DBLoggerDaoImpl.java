package com.cts.codetest.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cts.codetest.dao.DBLoggerDao;

@Repository(value = "dbLoggerDao")
public class DBLoggerDaoImpl implements DBLoggerDao {

	@Value("${db.enter.log}")
	private String loggingstatement;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void log(String logType, String activity, String message, String details) {
		jdbcTemplate.update(loggingstatement, new Object[] { logType, activity, message, details });
	}
}
