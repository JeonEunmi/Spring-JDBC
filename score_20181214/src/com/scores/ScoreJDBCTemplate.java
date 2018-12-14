package com.scores;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class ScoreJDBCTemplate implements ScoreDAO{
	
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

	// ȸ�� ���� �Է� �޼ҵ�
	public int scoreAdd(Score s) {
		int result = 0;

		String sql = "INSERT INTO scores (sid_, name_, subject1, subject2, subject3)"
				+ " VALUES ((SELECT CONCAT('S', LPAD(IFNULL(SUBSTR(MAX(sid_), 2), 0) + 1, 2, 0)) AS newId FROM scores s)"
				+ ", ?, ?, ?, ?)";

		result = this.jdbcTemplateObject.update(sql, s.getName_(), s.getSubject1(), s.getSubject2(), s.getSubject3());

		return result;
	}

	// ȸ�� ���� ��� �޼ҵ�
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
			// �ܺο��� �����ϴ� �ڷῡ ���� ���ε�
			// -> new Object[] {�ڷ�1, �ڷ�2, �ڷ�3, ...}
			result = this.jdbcTemplateObject.query(sql, new Object[] { value }, new ScoreRowMapper());
		}
		

		return result;
	}

}
