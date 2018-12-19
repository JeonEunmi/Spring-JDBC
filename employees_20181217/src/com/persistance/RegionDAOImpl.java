package com.persistance;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.domain.Region;
import com.mapper.RegionMapper;

public class RegionDAOImpl implements RegionDAO {

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
	public List<Region> list() {

		List<Region> result = new ArrayList<Region>();

		String sql = "SELECT regId, reg_name\r\n" + " ,(SELECT COUNT(*) FROM employees \r\n"
				+ " WHERE regId = r.regId) count_\r\n" + " FROM regions r\r\n" + " ORDER BY regId";

		result = this.jdbcTemplateObject.query(sql, new RegionMapper());

		return result;
	}

	@Override
	public int add(Region r) {

		int result = 0;

		String sql = "INSERT INTO regions (regId, reg_name)\r\n"
				+ " VALUES ((SELECT CONCAT('REG', LPAD(IFNULL(SUBSTR(MAX(regId), 4), 0) + 1, 2, 0)) \r\n"
				+ " AS newId FROM regions R), ?)";

		result = this.jdbcTemplateObject.update(sql, r.getReg_name());

		return result;
	}

	@Override
	public int remove(Region r) {

		int result = 0;

		String sql = "DELETE FROM regions\r\n" + " WHERE regId = ?";

		result = this.jdbcTemplateObject.update(sql, r.getRegId());

		return result;
	}

}
