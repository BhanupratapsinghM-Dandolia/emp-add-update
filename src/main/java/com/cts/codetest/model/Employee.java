package com.cts.codetest.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.springframework.format.annotation.DateTimeFormat;

@XmlRootElement(name="employee")
public class Employee {
	
	int empNo;
	
	String empName;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date joiningDate;
	
	private  String department;
	
	public int getEmpNo() {
		return empNo;
	}
	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	
	@XmlJavaTypeAdapter(DateFormatterAdapter.class)
	public Date getJoiningDate() {
		return joiningDate;
	}
	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	@Override
	public String toString() {
		return "Employee [empNo=" + empNo + ", empName=" + empName + ", joiningDate=" + joiningDate + ", department="
				+ department + "]";
	}
	
	private static class DateFormatterAdapter extends XmlAdapter<String, Date> {
        private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        @Override
        public Date unmarshal(final String v) throws Exception {
            return dateFormat.parse(v);
        }

        @Override
        public String marshal(final Date v) throws Exception {
            return dateFormat.format(v);
        }
    }
}
