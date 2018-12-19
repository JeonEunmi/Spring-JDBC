package com.persistance;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.domain.Department;
import com.mapper.DepartmentMapper;

public class DepartmentDAOImpl implements DepartmentDAO {

	private DataSource dataSource;

	// Spring JDBC���� �����ϴ� ���� ���� ���� ��ü
	private JdbcTemplate jdbcTemplateObject;

	/* setter ��� ���� ���� ���� */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		/* �����ͺ��̽� Ŀ�ؼ� ���� ���� */
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	/* �����ͺ��̽� Ŀ�ؼ� ���� Ȯ�� */
	public void connectionTest() {
		try {
			System.out.println(this.dataSource.getConnection().toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Department> list() {

		List<Department> result = new ArrayList<Department>();

		String sql = "SELECT deptId, dept_name\r\n"
				+ "  , (SELECT COUNT(*) FROM employees WHERE deptId = d.deptId) count_\r\n" + "  FROM departments d\r\n"
				+ "  ORDER BY deptId";

		result = this.jdbcTemplateObject.query(sql, new DepartmentMapper());

		return result;
	}

	@Override
	public int add(Department d) {
		int result = 0;

		String sql = "INSERT INTO departments (deptId, dept_name) \r\n"
				+ "     VALUES (( (SELECT CONCAT('DEPT', LPAD( IFNULL(SUBSTR(MAX(deptId),5),0)+1, 2, 0 )) AS newId FROM departments D) ), ?)";

		result = this.jdbcTemplateObject.update(sql, d.getDept_name());

		return result;
	}

	@Override
	public int remove(Department d) {
		int result = 0;

		String sql = "DELETE FROM departments WHERE deptId = ?";

		result = this.jdbcTemplateObject.update(sql, d.getDeptId());

		return result;
	}

}
