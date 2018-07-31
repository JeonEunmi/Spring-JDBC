package com.employee.persistance;

import java.util.Arrays;
import java.util.Objects;

import com.employee.domain.Department;
import com.employee.domain.Employee;
import com.employee.domain.Position;
import com.employee.domain.Region;

// (배열) 자료 저장소 운영 클래스
public class EmployeeDAO {

	// 저장소 준비 -> 자료형 클래스별로 별도 준비
	private Employee[] employees = new Employee[10];
	private Department[] departments = new Department[5];
	private Region[] regions = new Region[5];
	private Position[] positions = new Position[5];

	// 배열 인덱스 관리
	private int regionIdx;
	private int departmentIdx;
	private int positionIdx;
	private int employeeIdx;

	// 생성자 -> 초기화
	public EmployeeDAO() {

		// 지역, 부서, 직위 정보에 대한 초기값 준비

		// 지역 정보 초기값
		/*
		 * REG01-강원 REG02-경기 REG03-경남 REG04-경북 REG05-제주
		 */
		this.regions[this.regionIdx] = new Region("REG01", "강원");
		++this.regionIdx;
		this.regions[this.regionIdx] = new Region("REG02", "경기");
		++this.regionIdx;
		this.regions[this.regionIdx] = new Region("REG03", "서울");
		++this.regionIdx;

		// 부서 정보 초기값
		/*
		 * DEPT001-개발부 DEPT002-기획부 DEPT003-영업부
		 */
		this.departments[this.departmentIdx] = new Department("DEPT001", "개발부");
		++this.departmentIdx;
		this.departments[this.departmentIdx] = new Department("DEPT002", "기획부");
		++this.departmentIdx;
		this.departments[this.departmentIdx] = new Department("DEPT003", "총무부");
		++this.departmentIdx;
		
		// 직위 정보 초기값
		/*
		JOB01-과장-2000000
		JOB02-대리-1500000
		JOB03-사원-1000000
		*/
		this.positions[this.positionIdx] = new Position("JOB01", "부장", 2000000);
		++this.positionIdx;
		this.positions[this.positionIdx] = new Position("JOB02", "대리", 1500000);
		++this.positionIdx;
		this.positions[this.positionIdx] = new Position("JOB03", "사원", 1000000);
		++this.positionIdx;
		
		// 직원 정보 초기값 준비
		this.employees[this.employeeIdx] = new Employee("EMP002","홍길동","771212-1022432"
				, "1998-10-11","011-2356-4528", "REG03", "DEPT002","JOB01", 2610000, 200000);
		++this.employeeIdx;
		this.employees[this.employeeIdx] = new Employee("EMP001","이순신","801007-1544236"
				, "2000-11-29","010-4758-6532", "REG02", "DEPT003","JOB03", 1320000, 200000);
		++this.employeeIdx;
		// -> 지역명(부서명, 직위명)이 아니라 지역번호(부서번호, 직위번호)를 입력한다.
		// -> 급여 항목은 입력하지 않는다.
	}
	
// ===================================================================================
	
	// 지역번호 자동 증가 메소드 추가
	public String autoRegionID() {
		String regionNum = String.format("REG%02d", this.regionIdx+1);
		return regionNum;
	}


	// 지역 정보 입력
	public void regionAdd(Region r) {
		// 외부에서 전달된 지역 정보를 저장소에 저장
		// 인덱스 관리
		this.regions[this.regionIdx] = r;
		++this.regionIdx;
	}

	// 지역 정보 출력
	public Region[] regionList() {
		// 저장소에 저장된 지역정보 전체를 사본 배열 만들어서 반환
		// null 값은 제외
		int count = 0;

		Region[] rTmp = new Region[this.regionIdx];
		for (Region r : this.regions) {
			if (Objects.isNull(r) != true) {
				rTmp[count] = r;
				++count;
			}
		}
		return Arrays.copyOfRange(rTmp, 0, count);
	}

	// 지역 정보 검색 -> 지역 번호 기준 -> String key, String value
	public Region regionSearch(String key, String value) {

		// 저장소에 저장된 지역 정보 중에서
		// 특정 조건을 만족하는 정보만을 반환
		// null 값은 제외
		Region region = null;
		
		for(Region r : this.regions) {
			if(!Objects.isNull(r)) {
				if(r.getRegionID().equals(value)) {
					region = r;
				}
			}
		}
		return region; // 검색된 지역 정보 객체 or null
	}
	
// =================================================================================

	// 부서번호 자동 증가 메소드 추가
	public String autoDepartmentID() {
		String departmentNum = String.format("DEPT%03d", this.departmentIdx+1);
		return departmentNum;
	}
	
	// 부서 정보 입력
	public void departmentAdd(Department d) {
		this.departments[this.departmentIdx] = d;
		++this.departmentIdx;
	}

	// 부서 정보 출력
	public Department[] departmentList() {
		int count = 0;

		Department[] dTmp = new Department[this.departmentIdx];
		for (Department d : this.departments) {
			if (Objects.isNull(d) != true) {
				dTmp[count] = d;
				++count;
			}
		}
		return Arrays.copyOfRange(dTmp, 0, count);
	}

	// 부서 정보 검색 -> 부서 번호 기준 -> String key, String value
	public Department departmentSearch(String key, String value) {
		Department department = null;
		
		for(Department d : this.departments) {
			if(!Objects.isNull(d)) {
				if(d.getDepartmentID().equals(value)) {
					department = d;
				}
			}
		}
		return department; // 검색된 지역 정보 객체 or null
	}
	
	
// ===============================================================================
	
	// 직위번호 자동 증가 메소드 추가
	public String autoPositionID() {
		String positionNum = String.format("JOB%02d", this.positionIdx+1);
		return positionNum;
	}
	// 직위 정보 입력
	public void positionAdd(Position p) {
		this.positions[this.positionIdx] = p;
		++this.positionIdx;
	}

	// 직위 정보 출력
	public Position[] positionList() {
		int count = 0;

		Position[] pTmp = new Position[this.positionIdx];
		for (Position p : this.positions) {
			if (Objects.isNull(p) != true) {
				pTmp[count] = p;
				++count;
			}
		}
		return Arrays.copyOfRange(pTmp, 0, count);
	}

	// 직위 정보 검색 -> 직위 번호 기준 -> String key, String value
	public Position positionSearch(String key, String value) {
		Position position = null;
		
		for(Position p : this.positions) {
			if(!Objects.isNull(p)) {
				if(p.getPositionID().equals(value)) {
					position = p;
				}
			}
		}
		return position; // 검색된 지역 정보 객체 or null
		
		
	}
	
// ===============================================================================
		
	// 직원사번 자동 증가 메소드 추가
	public String autoEmployeeID() {
		String employeeNum = String.format("EMP%03d", this.employeeIdx+1);
		return employeeNum;
	}
	
	// 직원 정보 입력
	public void employeeAdd(Employee e) {
		this.employees[this.employeeIdx] = e;
		++this.employeeIdx;
	}

	// 직원 정보 전체 출력 -> 기준에 따른 정렬 액션 추가 -> String key
	// -> null 값 없는 사본 배열 반환
	public Employee[] employeeList() {

		Employee[] employees = new Employee[this.employeeIdx];
		int a = 0;
		
		for(Employee e : this.employees) {
			if(!Objects.isNull(e)) {
				employees[a] = e;
				++a;
			}
		}
		return Arrays.copyOfRange(employees, 0, a);
	}

	// 직원 정보 검색 -> 기준에 따른 정렬 액션 추가 -> String key, String value
	public Employee[] employeeSearch(String key, String value) {
		
		Employee[] employees = new Employee[this.employeeIdx];
		int a = 0;
		
		for(Employee p : this.employees) {
			if(!Objects.isNull(p)) {
				String temp = "";
				if(key.equals("employeeId")) {
					temp = p.getemployeeID();
				}
				if(key.equals("employeeName")) {
					temp = p.getEmployeeName();
				}
				if(key.equals("department")) {
					temp = this.departmentSearch("departmentId", p.getDepartmentID()).getDepartmentName();
				}
				if(key.equals("region")) {
					temp = this.regionSearch("regionId", p.getRegionID()).getRegionName();
				}
				if(key.equals("position")) {
					temp = this.positionSearch("positionId", p.getPositionID()).getPositionName();
				}
				if(temp.equals(value)) {
					employees[a] = p;
					++a;
				}
			}
		}
		return Arrays.copyOfRange(employees, 0, a); // 검색된 지역 정보 객체 or null
	}
}

