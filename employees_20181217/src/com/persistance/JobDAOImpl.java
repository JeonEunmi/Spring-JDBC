package com.persistance;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.domain.Job;
import com.mapper.JobMapper;

public class JobDAOImpl implements JobDAO {

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
	public List<Job> list() {

		List<Job> result = new ArrayList<Job>();

		String sql = "SELECT jobId, job_title, min_basicPay\r\n" + "          ,(SELECT COUNT(*) FROM employees \r\n"
				+ "                  WHERE jobId = j.jobId) count_\r\n" + "          FROM jobs j\r\n"
				+ "          ORDER BY jobId";

		result = this.jdbcTemplateObject.query(sql, new JobMapper());

		return result;
	}

	@Override
	public int add(Job j) {
		int result = 0;

		String sql = "INSERT INTO jobs (jobId, job_title, min_basicPay)\r\n"
				+ "          VALUES ((SELECT CONCAT('JOB', LPAD(IFNULL(SUBSTR(MAX(jobId), 4), 0) + 1, 2, 0))\r\n"
				+ "          AS newId FROM jobs J), ?, ?)";

		result = this.jdbcTemplateObject.update(sql, j.getJob_title(), j.getMin_basicPay());

		return result;
	}

	@Override
	public int remove(Job j) {
		int result = 0;

		String sql = "DELETE FROM jobs WHERE jobId = ?";

		result = this.jdbcTemplateObject.update(sql, j.getJobId());

		return result;
	}

}
