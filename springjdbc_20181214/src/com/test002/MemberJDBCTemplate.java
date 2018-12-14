package com.test002;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class MemberJDBCTemplate {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;

	/* setter ��� ���� ���� ���� */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		/* ������ ���̽� Ŀ�ؼ� ���� ���� */
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	/* ������ ���̽� Ŀ�ؼ� ���� Ȯ�� */
	public void connectionTest() {
		try {
			System.out.println(this.dataSource.getConnection().toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// ��ü ���
	public List<Member> memberList() {
		String sql = "SELECT mid_, name_, phone FROM members";

		// JDBCTemplate Ŭ������
		// query() �޼ҵ�� ���� ���� �� �÷��� ��ȯ
		List<Member> result = this.jdbcTemplateObject.query(sql, new MemberRowMapper());

		return result;
	}

	// �˻� ���
	public List<Member> memberSearch(String key, String value) {
		String sql = "SELECT mid_, name_, phone FROM members ";

		// JDBCTemplate Ŭ������
		// query() �޼ҵ�� ���� ���� �� �÷��� ��ȯ

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
			// �ܺο��� �����ϴ� �ڷῡ ���� ���ε�
			// -> new Object[] {�ڷ�1, �ڷ�2, �ڷ�3, ...}
			result = this.jdbcTemplateObject.query(sql, new Object[] { value }, new MemberRowMapper());
		}

		return result;
	}

	// �Է��� ���
	public int add(Member m) {

		String sql = "INSERT INTO members(mid_, name_, phone) VALUES"
				+ " ((SELECT CONCAT('M', LPAD(IFNULL(SUBSTR(MAX(mid_), 2), 0)"
				+ " + 1, 2, 0)) AS newId FROM members m), ?, ?)";

		int result = this.jdbcTemplateObject.update(sql, m.getName_(), m.getPhone());

		return result;
	} 

	// ������ ���
	public int remove(String mid_) {

		String sql = "DELETE FROM members WHERE mid_=?";

		int result = this.jdbcTemplateObject.update(sql, mid_);

		return result;
	}

}
