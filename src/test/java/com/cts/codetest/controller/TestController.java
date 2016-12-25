package com.cts.codetest.controller;

import static com.jayway.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import com.cts.codetest.model.Employee;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;

@ActiveProfiles("unit-test")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:core-context.xml")
@WebAppConfiguration
public class TestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);
    
	@Mock
	public JdbcTemplate jdbcTemplate;

	@Autowired
	private WebApplicationContext context;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		RestAssuredMockMvc.webAppContextSetup(context);
	}

	@Test
	public void testCreateUpdateEmployeeSUCCESSCASE() {

		LOGGER.info("Running test createUpdateEmployee");
		Employee employee = new Employee();
		employee.setEmpNo(901);
		employee.setEmpName("dgsdf");
		employee.setJoiningDate(new Date());
		employee.setDepartment("IT");

		given()
			.accept(ContentType.XML)
			.contentType(ContentType.XML)
			.body("<employee>" + "<empNo>113</empNo>" + "<empName>dfkhgdf</empName>"
						+ "<joiningDate>2017-01-01</joiningDate>" + "<department>FIN</department>" + "</employee>")
		.when()
			.post("/emp")
		.then().apply(print())
			.statusCode(200);

	}
}
