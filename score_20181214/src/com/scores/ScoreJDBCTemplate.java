package com.scores;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class ScoreJDBCTemplate implements ScoreDAO{
	
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

	// 회원 정보 입력 메소드
	public int scoreAdd(Score s) {
		int result = 0;

		String sql = "INSERT INTO scores (sid_, name_, subject1, subject2, subject3)"
				+ " VALUES ((SELECT CONCAT('S', LPAD(IFNULL(SUBSTR(MAX(sid_), 2), 0) + 1, 2, 0)) AS newId FROM scores s)"
				+ ", ?, ?, ?, ?)";

		result = this.jdbcTemplateObject.update(sql, s.getName_(), s.getSubject1(), s.getSubject2(), s.getSubject3());

		return result;
	}

	// 회원 정보 출력 메소드
	public List<Score> scoreList(String key, String value) {

		/*
		 * CREATE OR REPLACE VIEW scoreView AS SELECT sid_, name_, subject1, subject2,
		 * subject3, total, avg_ , RANK() OVER(ORDER BY total DESC) rank_ FROM (SELECT
		 * sid_, name_, subject1, subject2, subject3 , (subject1 + subject2 + subject3)
		 * total , (subject1 + subject2 + subject3) / 3 avg_ FROM scores) ORDER BY sid_;
		 */

		List<Score> result = new ArrayList<Score>();

		String sql = "SELECT sid_, name_, subject1, subject2, subject3, total, avg_, rank_ FROM scoreView ";
		
		if (key.equals("all")) {
		} else if (key.equals("sid_")) {
			sql += "WHERE sid_=?";
		} else if (key.equals("name_")) {
			sql += "WHERE INSTR(name_,?) > 0 ";
		}

		sql += "ORDER BY sid_";

		if (key.equals("all")) {
			result = this.jdbcTemplateObject.query(sql, new ScoreRowMapper());
		} else {
			// 외부에서 제공하는 자료에 대한 바인딩
			// -> new Object[] {자료1, 자료2, 자료3, ...}
			result = this.jdbcTemplateObject.query(sql, new Object[] { value }, new ScoreRowMapper());
		}
		

		return result;
	}

}
