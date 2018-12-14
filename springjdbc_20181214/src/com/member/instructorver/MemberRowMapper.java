package com.member.instructorver;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class MemberRowMapper implements RowMapper<Member> {

	@Override
	public Member mapRow(ResultSet rs, int count) throws SQLException {
		//데이터베이스에서 읽어온 결과를
		//미리 준비한 객체의 특정 변수와 연결
		String mid_ = rs.getString("mid_");
		String name_ = rs.getString("name_");
		String phone = rs.getString("phone");
		String email = rs.getString("email");
		Date regDate = rs.getDate("regDate");
		return new Member(mid_, name_, phone, email, regDate);
	}

}
