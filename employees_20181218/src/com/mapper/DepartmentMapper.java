package com.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.domain.Department;

public class DepartmentMapper implements RowMapper<Department> {

	@Override
	public Department mapRow(ResultSet rs, int count) throws SQLException {
		String deptId = rs.getString("deptId");
		String dept_name = rs.getString("dept_name");
		int count_ = rs.getInt("count_");
		return new Department(deptId, dept_name, count_);
	}

}
