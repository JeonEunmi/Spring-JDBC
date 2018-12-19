package com.persistance;

import java.util.List;

import com.domain.Employee;

public interface EmployeeDAO {

	//전체 출력
	//1.사번정렬  2.이름정렬  3.지역명정렬  4.부서명정렬  5.직위명정렬
	public List<Employee> list(String key); 
		
	//검색
	//1.사번기준  2.이름기준  3.지역명기준  4.부서명기준  5.직위명기준
	public List<Employee> list(String key, String value);
	
	//입력
	public int add(Employee emp);
	
	//삭제
	public int remove(String empId);
	
}
