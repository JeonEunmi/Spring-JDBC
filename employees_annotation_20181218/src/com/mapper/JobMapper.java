package com.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.domain.Job;

public class JobMapper implements RowMapper<Job> {

	@Override
	public Job mapRow(ResultSet rs, int count) throws SQLException {
		String jobId = rs.getString("jobId");
		String job_title = rs.getString("job_title");
		int min_basicPay = rs.getInt("min_basicPay");
		int count_ = rs.getInt("count_");
		return new Job(jobId, job_title, min_basicPay, count_);
	}

}
