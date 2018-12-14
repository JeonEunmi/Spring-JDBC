package com.member;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class MemberRowMapper implements RowMapper<Member> {
	@Override
	public Member mapRow(ResultSet rs, int count) throws SQLException {

		String mid_ = rs.getString("mid_");
		String name_ = rs.getString("name_");
		String phone = rs.getString("phone");
		String email = rs.getString("email");
		Date regDate = rs.getDate("regDate");

		return new Member(mid_, name_, phone, email, regDate);
	}
}
