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

	// Spring JDBC에서 제공하는 쿼리 실행 전용 객체
	private JdbcTemplate jdbcTemplateObject;

	/* setter 방식 의존 주입 설정 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		/* 데이터베이스 커넥션 정보 전달 */
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	/* 데이터베이스 커넥션 상태 확인 */
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
