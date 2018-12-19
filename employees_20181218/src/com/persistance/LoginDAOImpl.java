package com.persistance;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.domain.Login;
import com.mapper.LoginMapper;

public class LoginDAOImpl implements LoginDAO {

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
	public Login login(String id_, String pw_) {
		
		Login result = null;

		String sql = "SELECT id_ FROM login WHERE id_=? AND pw_=?";
		try {
			//query() 메소드는 반환자료형이 List<특정자료형> 컬렉션
			//queryForObject() 메소드는 반환자료형이 특정자료형 객체
			//SELECT 쿼리 실행시 외부 자료 바인딩 방법 두 가지
			//1. ? 순서대로 값 나열 (가변매개변수 사용)
			result = this.jdbcTemplateObject.queryForObject(sql
					, new LoginMapper(), id_, pw_);
			//2. new Object[]{값1, ...} 제공
			/*
			result = this.jdbcTemplateObject.queryForObject(sql
					, new Object[] {id_, pw_}, new LoginMapper());
			*/
		}catch (Exception e) {
			//System.out.println(e.getMessage());
			//->Incorrect result size: expected 1, actual 0
		}
		
		return result;
	}

	@Override
	public int changePW(String id_, String oldPW_, String newPW_) {
		
		int result = 0;
		
		String sql = "UPDATE login SET pw_=? WHERE id_=? AND pw_=?";
		result = this.jdbcTemplateObject.update(sql
					, newPW_, id_, oldPW_);
		
		return result;
	}

}
