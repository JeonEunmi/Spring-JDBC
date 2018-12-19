package com.persistance;

import java.util.List;

import com.domain.Department;

public interface DepartmentDAO {

	// 출력
	public List<Department> list();

	// 입력
	public int add(Department d);

	// 삭제
	public int remove(Department d);
}
