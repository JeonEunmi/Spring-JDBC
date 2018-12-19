package com.persistance;
 
import java.util.List;
 
import javax.sql.DataSource;
 
import org.springframework.jdbc.core.JdbcTemplate;
 
import com.domain.Department;
import com.mapper.DepartmentMapper;
 
public class DepartmentDAOImpl implements DepartmentDAO {
    
    private DataSource dataSource;
 
    // Spring JDBC에서 제공하는 쿼리 실행 전용 객체
    private JdbcTemplate jdbcTemplateObject;
 
    // setter 방식 의존 주입 설정.
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }
 
    
    @Override
    public List<Department> list() {
        String sql="SELECT deptId, dept_name\r\n" + 
                "                ,(SELECT COUNT(*) FROM employees e WHERE e.deptId = d.deptId) count_\r\n" + 
                "                FROM departments d ORDER BY deptId";
        
        List<Department> result = this.jdbcTemplateObject.query(sql, new DepartmentMapper());
        return result;
    }
 
    @Override
    public int add(Department d) {
        String sql = "INSERT INTO departments (deptId, dept_name) "
                + " VALUES (( (SELECT CONCAT('DEPT', LPAD( IFNULL(SUBSTR(MAX(deptId),5),0)+1, 2, 0 ))"
                + " AS newId FROM departments d) ), ?)";
        int result = this.jdbcTemplateObject.update(sql, d.getDept_name());
                
        return result;
    }
 
    @Override
    public int remove(Department d) {
        String sql = "DELETE FROM departments WHERE deptId = ?";
        int result = this.jdbcTemplateObject.update(sql, d.getDeptId());
        return result;
    }
 
}
 