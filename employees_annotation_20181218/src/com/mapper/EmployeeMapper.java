package com.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

import org.springframework.jdbc.core.RowMapper;

import com.domain.Employee;

public class EmployeeMapper implements RowMapper<Employee> {

	@Override
	public Employee mapRow(ResultSet rs, int count) throws SQLException {
		
		String empid = rs.getString("empid");
		String name_ = rs.getString("name_");
		String ssn = rs.getString("ssn");
		Date hiredate = rs.getDate("hiredate");
		String phone = rs.getString("phone");
		String reg_name = rs.getString("reg_name");
		String dept_name = rs.getString("dept_name");
		String job_title = rs.getString("job_title");
		int basicpay = rs.getInt("basicpay");
		int extrapay = rs.getInt("extrapay");
		int pay = rs.getInt("pay");
		
		return new Employee(empid, name_, ssn, hiredate, phone, reg_name, dept_name, job_title
				, basicpay, extrapay, pay);
	}

}
