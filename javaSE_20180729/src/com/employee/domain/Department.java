package com.employee.domain;

// 부서 정보 자료형 클래스
public class Department {

	// 부서번호, 부서명
	// -> field, constructor, getter, setter, toSting();
	
	// 필드
	private String departmentID, departmentName;
	
	// 기본 생성자
	public Department() {
		
	}
	
	// 매개변수가 있는 생성자
	public Department(String departmentID, String departmentName) {
		this.departmentID = departmentID;
		this.departmentName = departmentName;
	}

	// getter, setter
	public String getDepartmentID() {
		return this.departmentID;
	}

	public void setDepartmentID(String departmentID) {
		this.departmentID = departmentID;
	}

	public String getDepartmentName() {
		return this.departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	// toString()
	@Override
	public String toString() {
		return this.getDepartmentID() + "-" + this.getDepartmentName();
		// return String.format("%s - %s" , this.getDepartmentID(), this.getDepartmentName());
	}
	
}
