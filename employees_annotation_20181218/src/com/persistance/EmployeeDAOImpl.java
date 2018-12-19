package com.persistance;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.domain.Employee;
import com.mapper.EmployeeMapper;

public class EmployeeDAOImpl implements EmployeeDAO {

	//�������� ����� ���� �� �������̽� �ڷ����� ����
	//->Ÿ�̾� �������̽�, �ѱ�Ÿ�̾� ��ü or ��ȣŸ�̾� ��ü
	private DataSource dataSource;
	
	//Spring JDBC���� �����ϴ� ���� ���� ���� ��ü
	private JdbcTemplate jdbcTemplateObject;

	/* setter ��� ���� ���� ���� */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		/* �����ͺ��̽� Ŀ�ؼ� ���� ���� */
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<Employee> list(String key) {
		
		/*
		CREATE OR REPLACE VIEW empview
		AS
		SELECT empId, name_, ssn, hiredate, phone, reg_name , dept_name, job_title, basicpay, extrapay, (basicPay+extraPay) AS pay
		    FROM employees e, regions r, departments d, jobs j
		    WHERE e.regId = r.regId
		      AND e.deptId = d.deptId
		      AND e.jobId = j.jobId
		    ORDER BY empId;
		*/
		
		String sql = "SELECT empId, name_, ssn, hiredate, phone, reg_name , dept_name, job_title, basicpay, extrapay, pay    \r\n" + 
				"	FROM empview\r\n";
		sql += String.format("    ORDER BY %s", key);
		
		List<Employee> result = this.jdbcTemplateObject.query(sql, new EmployeeMapper());
		
		return result;
	}

	@Override
	public List<Employee> list(String key, String value) {

		/*
		CREATE OR REPLACE VIEW empview
		AS
		SELECT empId, name_, ssn, hiredate, phone, reg_name , dept_name, job_title, basicpay, extrapay, (basicPay+extraPay) AS pay
		    FROM employees e, regions r, departments d, jobs j
		    WHERE e.regId = r.regId
		      AND e.deptId = d.deptId
		      AND e.jobId = j.jobId
		    ORDER BY empId;
		*/
		
		String sql = "SELECT empId, name_, ssn, hiredate, phone, reg_name , dept_name, job_title, basicpay, extrapay, pay    \r\n" + 
				"	FROM empview\r\n";
		if (key.equals("all")) {
		} else if (key.equals("empId")) {
			sql += " WHERE empId = ?\r\n";
		} else if (key.equals("name_")) {
			sql += " WHERE INSTR(name_,?) > 0 \r\n";
		} else if (key.equals("reg_name")) {
			sql += " WHERE INSTR(reg_name,?) > 0\r\n";
		} else if (key.equals("dept_name")) {
			sql += " WHERE INSTR(dept_name,?) > 0\r\n";
		} else if (key.equals("job_title")) {
			sql += " WHERE INSTR(job_title,?) > 0\r\n";
		}
		sql += "    ORDER BY empId";

		List<Employee> result = null; 
		
		if (key.equals("all")) {
			result = this.jdbcTemplateObject.query(sql, new EmployeeMapper());
		} else {
			result = this.jdbcTemplateObject.query(sql, new EmployeeMapper(), value);
		}
		
		return result;
	}

	@Override
	public int add(Employee emp) {
		String sql = "INSERT INTO employees  (empId, name_, ssn, hiredate, phone\r\n"
				+ "    , regId, deptId, jobId, basicpay, extrapay)\r\n"
				+ "    VALUES ((SELECT CONCAT('EMP', LPAD(IFNULL(SUBSTR(MAX(empId), 4), 0) + 1, 3, 0)) \r\n"
				+ "        AS newId FROM employees E), ?, ?, ?\r\n"
				+ "        , ?, ?, ?, ?, ?, ?)";
		
		int result = this.jdbcTemplateObject.update(sql
					, emp.getName_(), emp.getSsn(), emp.getSsn(), emp.getPhone()
					, emp.getRegId(), emp.getDeptId(), emp.getJobId()
					, emp.getBasicpay(), emp.getExtrapay());
		return result;
	}

	@Override
	public int remove(String empId) {
		String sql = "DELETE FROM employees WHERE empId = ?";
		int result = this.jdbcTemplateObject.update(sql
					,empId);
		return result;
	}

}
