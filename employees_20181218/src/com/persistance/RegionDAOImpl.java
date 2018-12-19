package com.persistance;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.domain.Region;
import com.mapper.RegionMapper;

public class RegionDAOImpl implements RegionDAO {

	//의존주입 대상을 받을 때 인터페이스 자료형을 권장
	//->타이어 인터페이스, 한국타이어 객체 or 금호타이어 객체
	private DataSource dataSource;
	
	//Spring JDBC에서 제공하는 쿼리 실행 전용 객체
	private JdbcTemplate jdbcTemplateObject;

	/* setter 방식 의존 주입 설정 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		/* 데이터베이스 커넥션 정보 전달 */
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<Region> list() {

		String sql = "SELECT regId, reg_name\r\n" + 
				" ,(SELECT COUNT(*) FROM employees \r\n" + 
				" WHERE regId = r.regId) count_\r\n" + 
				" FROM regions r\r\n" + 
				" ORDER BY regId";
		
		List<Region> result = this.jdbcTemplateObject.query(sql
					, new RegionMapper());
		
		return result;
	}

	@Override
	public int add(Region r) {
		
		String sql = "INSERT INTO regions (regId, reg_name)\r\n"
				+ " VALUES ((SELECT CONCAT('REG', LPAD(IFNULL(SUBSTR(MAX(regId), 4), 0) + 1, 2, 0)) \r\n"
				+ " AS newId FROM regions R), ?)";
		
		int result = this.jdbcTemplateObject.update(sql, r.getReg_name());
		
		return result;
	}

	@Override
	public int remove(Region r) {
		
		String sql = "DELETE FROM regions\r\n" 
					+ " WHERE regId = ?";
		
		int result = this.jdbcTemplateObject.update(sql, r.getRegId());

		return result;
	}

}
