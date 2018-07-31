package com.employee.domain;

// 직원 정보 저장 자료형 클래스
public class Employee {

	// - 입력 항목은 이름, 주민번호, 입사일, 전화번호, 지역번호, 부서번호, 직위번호, 기본급, 수당 이다.
	// - 출력 항목은 사번, 이름, 주민번호, 입사일, 전화번호, 지역명, 부서명, 직위명, 기본급, 수당, 급여로 구성된다.
	
	// 사번, 이름, 주민번호, 입사일, 전화번호, 지역번호, 부서번호, 직위번호, 기본급, 수당, 급여
	
	private String employeeID, employeeName, ssn, joinDate, callNum, regionID, departmentID, positionID;
	private int basePay, extraPay, pay;
	public Employee() {
		
	}
	
	//getter 전용 -> employeeID, pay
	
	public Employee(String employeeID, String employeeName, String ssn, String joinDate, String callNum, String regionID,
			String departmentID, String positionID, int basePay, int extraPay) {
		this.employeeID = employeeID;
		this.employeeName = employeeName;
		this.ssn = ssn;
		this.joinDate = joinDate;
		this.callNum = callNum;
		this.regionID = regionID;
		this.departmentID = departmentID;
		this.positionID = positionID;
		this.basePay = basePay;
		this.extraPay = extraPay;
	}
	public String getEmployeeName() {
		return this.employeeName;
	}
	public void setEmployName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getSsn() {
		return this.ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public String getJoinDate() {
		return this.joinDate;
	}
	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}
	public String getCallNum() {
		return this.callNum;
	}
	public void setCallNum(String callNum) {
		this.callNum = callNum;
	}
	public String getRegionID() {
		return this.regionID;
	}
	public void setRegionID(String regionID) {
		this.regionID = regionID;
	}
	public String getDepartmentID() {
		return this.departmentID;
	}
	public void setDepartmentID(String departmentID) {
		this.departmentID = departmentID;
	}
	public String getPositionID() {
		return this.positionID;
	}
	public void setPositionID(String positionID) {
		this.positionID = positionID;
	}
	public int getBasePay() {
		return this.basePay;
	}
	public void setBasePay(int basePay) {
		this.basePay = basePay;
	}
	public int getExtraPay() {
		return this.extraPay;
	}
	public void setExtraPay(int extraPay) {
		this.extraPay = extraPay;
	}
	public String getemployeeID() {
		return this.employeeID;
	}
	public int getPay() {
		return this.getBasePay() + this.getExtraPay();
	}	
	
	
}
