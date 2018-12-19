package com.persistance;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.domain.Login;
import com.mapper.LoginMapper;

public class LoginDAOImpl implements LoginDAO {

	//�������� ����� ���� �� �������̽� �ڷ����� ����
	//->Ÿ�̾� �������̽�, �ѱ�Ÿ�̾� ��ü or ��ȣŸ�̾� ��ü
	private DataSource dataSource;
	
	//Spring JDBC���� �����ϴ� ���� ���� ���� ��ü
	private JdbcTemplate jdbcTemplateObject;

	/* setter ��� ���� ���� ���� */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		/* �����ͺ��̽� Ŀ�ؼ� ���� ���� */
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}
	
	@Override
	public Login login(String id_, String pw_) {
		
		Login result = null;

		String sql = "SELECT id_ FROM login WHERE id_=? AND pw_=?";
		try {
			//query() �޼ҵ�� ��ȯ�ڷ����� List<Ư���ڷ���> �÷���
			//queryForObject() �޼ҵ�� ��ȯ�ڷ����� Ư���ڷ��� ��ü
			//SELECT ���� ����� �ܺ� �ڷ� ���ε� ��� �� ����
			//1. ? ������� �� ���� (�����Ű����� ���)
			result = this.jdbcTemplateObject.queryForObject(sql
					, new LoginMapper(), id_, pw_);
			//2. new Object[]{��1, ...} ����
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
