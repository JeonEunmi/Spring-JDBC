package com.scores;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ScoreRowMapper implements RowMapper<Score> {

	@Override
	public Score mapRow(ResultSet rs, int count) throws SQLException {
		// 데이터베이스에서 읽어온 결과를
		// 미리 준비한 객체의 특정 변수와 연결
		String sid_ = rs.getString("sid_");
		String name_ = rs.getString("name_");
		int subject1 = rs.getInt("subject1");
		int subject2 = rs.getInt("subject2");
		int subject3 = rs.getInt("subject3");
		int total = rs.getInt("total");
		Double avg_ = rs.getDouble("avg_");
		int rank_ = rs.getInt("rank_");
		return new Score(sid_, name_, subject1, subject2, subject3
				, total, avg_, rank_);
	}
}
