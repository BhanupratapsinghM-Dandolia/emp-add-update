package com.cts.codetest.dao;

public interface DBLoggerDao {

	void log(String logType, String activity, String message, String detail);
}
