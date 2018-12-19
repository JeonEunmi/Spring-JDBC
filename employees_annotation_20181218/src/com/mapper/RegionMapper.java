package com.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.domain.Region;

public class RegionMapper implements RowMapper<Region> {

	@Override
	public Region mapRow(ResultSet rs, int count) throws SQLException {
		String regId = rs.getString("regId");
		String reg_name = rs.getString("reg_name");
		int count_ = rs.getInt("count_");
		return new Region(regId, reg_name, count_);
	}

}
