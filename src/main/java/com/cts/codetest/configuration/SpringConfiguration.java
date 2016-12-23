package com.cts.codetest.configuration;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.core.JdbcTemplate;

import com.cts.codetest.aspects.LoggingAspect;
import com.cts.codetest.filter.LogFilter;

@Configuration
@EnableAspectJAutoProxy
public class SpringConfiguration {
	
	@Value("${db.url}")
	private String dbUrl; 
	
	@Value("${db.driver}")
	private String dbDriver;
	
	@Value("${db.username}")
	private String dbUsername;
	
	@Value("${db.password}")
	private String dbPassword;

	@Bean
	public LoggingAspect loggingAspect(){
		return new LoggingAspect();
	}
	@Bean
	public DataSource dataSource() {

	    BasicDataSource dataSource = new BasicDataSource();
	    dataSource.setUrl(dbUrl);
	    dataSource.setDriverClassName(dbDriver);
	    dataSource.setUsername(dbUsername);
	    dataSource.setPassword(dbPassword);
	    return dataSource;
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate(){
		return new JdbcTemplate(dataSource());
	}
	
	@Bean
	public LogFilter logFilter() {
		return new LogFilter();
	}
}
