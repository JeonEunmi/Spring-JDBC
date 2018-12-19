package com.persistance;
 
import java.util.List;
 
import javax.sql.DataSource;
 
import org.springframework.jdbc.core.JdbcTemplate;
 
import com.domain.Job;
import com.mapper.JobMapper;
 
public class JobDAOImpl implements JobDAO{
    
    private DataSource dataSource;
 
    // Spring JDBC에서 제공하는 쿼리 실행 전용 객체
    private JdbcTemplate jdbcTemplateObject;
 
    // setter 방식 의존 주입 설정.
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }
 
 
    @Override
    public List<Job> list() {
        String sql ="SELECT jobId, job_title, min_basicPay"
                + ", (SELECT COUNT(*) FROM employees WHERE jobId = j.jobId) count_ " +
                    "FROM jobs j ORDER BY jobId";
        List<Job> result = this.jdbcTemplateObject.query(sql, new JobMapper());
        
        return result;
    }
 
    @Override
    public int add(Job j) {
        String sql ="INSERT INTO jobs (jobId, job_title, min_basicPay)\r\n"
                + "          VALUES ((SELECT CONCAT('JOB', LPAD(IFNULL(SUBSTR(MAX(jobId), 4), 0) + 1, 2, 0))\r\n"
                + "          AS newId FROM jobs J), ?, ?)";
        
        int result = this.jdbcTemplateObject.update(sql, j.getJob_title(), j.getMin_basicPay());
        return result;
    }
 
    @Override
    public int remove(Job j) {
        String sql = "DELETE FROM jobs  WHERE jobId = ?";
        int result =  this.jdbcTemplateObject.update(sql, j.getJobId());
        return result;
    }
 
}