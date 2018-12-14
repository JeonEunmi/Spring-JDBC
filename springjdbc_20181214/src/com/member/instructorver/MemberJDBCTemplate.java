package com.member.instructorver;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class MemberJDBCTemplate implements MemberDAO {

	private DataSource dataSource;
	
	//Spring JDBC에서 제공하는 쿼리 실행 전용 객체
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
	
	//회원 정보 입력 메소드
	public int memberAdd(Member m) {
		int result = 0;
		
		String sql = "INSERT INTO members (mid_, name_, phone, email, regDate)\r\n" + 
				"	VALUES ((SELECT CONCAT('M', LPAD(IFNULL(SUBSTR(MAX(mid_), 2), 0) + 1, 2, 0)) AS newId FROM members m)\r\n" + 
				"	, ?, ?, ?, NOW())";
		
		result = this.jdbcTemplateObject.update(sql
				, m.getName_(), m.getPhone(), m.getEmail());
		
		return result;
	}
	
	//회원 정보 출력 메소드
	public List<Member> memberList() {
		List<Member> result = new ArrayList<Member>();

		String sql = "SELECT mid_, name_, phone, email, regDate\r\n" + 
				"	FROM members\r\n" + 
				"	ORDER BY mid_";
		
		result = this.jdbcTemplateObject.query(sql, new MemberRowMapper());

		return result;
	}
	
}
