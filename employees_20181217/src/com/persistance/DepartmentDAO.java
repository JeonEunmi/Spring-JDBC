package com.persistance;

import java.util.List;

import com.domain.Department;

public interface DepartmentDAO {

	// ���
	public List<Department> list();

	// �Է�
	public int add(Department d);

	// ����
	public int remove(Department d);
}
