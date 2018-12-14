package com.test002;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class MemberJDBCTemplate {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;

	/* setter 방식 의존 주입 설정 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		/* 데이터 베이스 커넥션 정보 전달 */
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	/* 데이터 베이스 커넥션 상태 확인 */
	public void connectionTest() {
		try {
			System.out.println(this.dataSource.getConnection().toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 전체 출력
	public List<Member> memberList() {
		String sql = "SELECT mid_, name_, phone FROM members";

		// JDBCTemplate 클래스의
		// query() 메소드는 쿼리 실행 후 컬렉션 반환
		List<Member> result = this.jdbcTemplateObject.query(sql, new MemberRowMapper());

		return result;
	}

	// 검색 출력
	public List<Member> memberSearch(String key, String value) {
		String sql = "SELECT mid_, name_, phone FROM members ";

		// JDBCTemplate 클래스의
		// query() 메소드는 쿼리 실행 후 컬렉션 반환

		if (key.equals("all")) {
		} else if (key.equals("mid_")) {
			sql += "WHERE mid_=?";
		} else if (key.equals("name_")) {
			sql += "WHERE INSTR(name_,?) > 0 ";
		} else if (key.equals("phone")) {
			sql += "WHERE INSTR(phone,?) > 0 ";
		}
		sql += "ORDER BY mid_";

		List<Member> result = null;

		if (key.equals("all")) {
			result = this.jdbcTemplateObject.query(sql, new MemberRowMapper());
		} else {
			// 외부에서 제공하는 자료에 대한 바인딩
			// -> new Object[] {자료1, 자료2, 자료3, ...}
			result = this.jdbcTemplateObject.query(sql, new Object[] { value }, new MemberRowMapper());
		}

		return result;
	}

	// 입력인 경우
	public int add(Member m) {

		String sql = "INSERT INTO members(mid_, name_, phone) VALUES"
				+ " ((SELECT CONCAT('M', LPAD(IFNULL(SUBSTR(MAX(mid_), 2), 0)"
				+ " + 1, 2, 0)) AS newId FROM members m), ?, ?)";

		int result = this.jdbcTemplateObject.update(sql, m.getName_(), m.getPhone());

		return result;
	} 

	// 삭제인 경우
	public int remove(String mid_) {

		String sql = "DELETE FROM members WHERE mid_=?";

		int result = this.jdbcTemplateObject.update(sql, mid_);

		return result;
	}

}
