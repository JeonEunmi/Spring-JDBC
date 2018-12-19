package com.persistance;

import java.util.List;

import com.domain.Employee;

public interface EmployeeDAO {

	//��ü ���
	//1.�������  2.�̸�����  3.����������  4.�μ�������  5.����������
	public List<Employee> list(String key); 
		
	//�˻�
	//1.�������  2.�̸�����  3.���������  4.�μ������  5.���������
	public List<Employee> list(String key, String value);
	
	//�Է�
	public int add(Employee emp);
	
	//����
	public int remove(String empId);
	
}
