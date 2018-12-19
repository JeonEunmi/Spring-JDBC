package com.persistance;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.domain.Login;
import com.mapper.LoginMapper;

public class LoginDAOImpl implements LoginDAO {

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
	public Login login(Login l) {
		
		Login result = null;

		String sql = "SELECT id_, pw_\r\n" + 
				"		    FROM login\r\n" + 
				"		    WHERE id_=? AND pw_=?";

		result = this.jdbcTemplateObject.queryForObject(sql, new Object[] { l.getId_(), l.getPw_() }, new LoginMapper());

		return result;
	}

	@Override
	public int add(Login l) {
		int result = 0;

		String sql = "INSERT INTO login (id_, pw_) \r\n" + 
				"VALUES (?, ?)";

		result = this.jdbcTemplateObject.update(sql, l.getId_(), l.getPw_());

		return result;
	}

	@Override
	public int pwupdate(Login l) {
		int result = 0;

		String sql = "UPDATE login SET pw_ = ? \r\n" + 
				"WHERE id_ = ? AND pw_ = ?";

		result = this.jdbcTemplateObject.update(sql, l.getNewPw_(), l.getId_(), l.getPw_());

		return result;
	}

}
