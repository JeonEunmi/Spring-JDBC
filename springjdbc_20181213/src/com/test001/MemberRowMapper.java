package com.test001;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class MemberRowMapper implements RowMapper<Member> {
	@Override
	public Member mapRow(ResultSet rs, int count) throws SQLException {

		String mid_ = rs.getString("mid_");
		String name_ = rs.getString("name_");
		String phone = rs.getString("phone");

		return new Member(mid_, name_, phone);
	}
}
