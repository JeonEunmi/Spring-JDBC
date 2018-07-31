package com.employee.service;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.employee.domain.Department;
import com.employee.domain.Employee;
import com.employee.domain.Position;
import com.employee.domain.Region;
import com.employee.persistance.EmployeeDAO;

// 메인 메뉴별 액션 클래스
public class SubMain {

	// EmployeeDAO 클래스의 객체 생성 및 필드 구성
	private EmployeeDAO dao = new EmployeeDAO();

	// 서브 메뉴 운영 메소드
	// ===================================================================================
	// 직원 관리 v1.0 > 직원 관리
	// 1. 직원 입력 2. 직원 전체 출력 3. 직원 검색
	public void mainMenu1(Scanner sc) {
		System.out.println();

		while (true) {

			try {
				System.out.println("=================");
				System.out.println(" ★직원 관리 v1.0 > 직원 관리");
				System.out.println("=================");

				System.out.println("1. 직원 입력 | 2. 직원 전체 출력 | 3. 직원 검색");
				System.out.print("선택(1~3, 0 exit)?  ");

				int selectNum = 0;
				selectNum = sc.nextInt();
				sc.nextLine();

				if (selectNum == 0) {
					break;
				}

				switch (selectNum) {

				case 1:
					this.mainMenu1_sub1(sc);
					break;
				case 2:
					this.mainMenu1_sub2(sc);
					break;
				case 3:
					this.mainMenu1_sub3(sc);
					break;
				}

			} catch (Exception e) {
				sc.nextLine();
				System.out.println("♨♨ 잘못된 접근 입니다.");
			}
			System.out.println();
		}

	}

	// ===================================================================================

	// 직원 관리 v1.0 > 직원 관리 > 직원입력
	private void mainMenu1_sub1(Scanner sc) {
		
		System.out.println();
		System.out.print("＊이름?  ");
		String name = sc.nextLine();
		
		String ssn = "";
		String regExp1 = "\\d{6}-\\d{7}";
		
		while(true) {
			System.out.println();
			System.out.print("＊주민번호(YYMMDD-NNNNNNN)?  ");
			ssn = sc.nextLine();
			
			if(Pattern.matches(regExp1, ssn) == false) {
				System.out.println();
				System.out.println("================================");
				System.out.println("            *경고*");
				System.out.println("주민번호 형식이 잘못 되었습니다.");
				System.out.println("================================");
			} else {
				break;
			}
			
		}
		
		// 올바른 날짜인지 검사하는 과정 추가 
		String joinDate = "";		
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		sf.setLenient(false);
		
		while(true) {
			try {
				System.out.println();
				System.out.print("＊입사일(YYYY-MM-DD)?  ");
				joinDate = sc.nextLine();
				
				Date d = sf.parse(joinDate);
				// 올바른 날짜인 경우 정상 출력
				
				break;
				
			} catch (Exception e1) {
				// 틀린 날짜인 경우 예외 발생
				System.out.println();
				System.out.println("================================");
				System.out.println("            *경고*");
				System.out.println("  날짜 형식이 잘못 되었습니다.");
				System.out.println("================================");
			}
			
		}
		
		
		
		String callNum = "";
		String regExp3 = "(02|010)-\\d{3,4}-\\d{4}";
		
		while(true) {
			System.out.println();
			System.out.print("＊전화번호(000-XXXX-XXXX)?  ");
			callNum = sc.nextLine();
			
			if(Pattern.matches(regExp3, callNum) == false) {
				System.out.println();
				System.out.println("================================");
				System.out.println("            *경고*");
				System.out.println("전화번호 형식이 잘못 되었습니다.");
				System.out.println("================================");
			} else {
				break;
			}
			
		}

		this.mainMenu2_sub1_sub2();
		String regionId = "";
		
		while(true) {
			System.out.println();
			System.out.print("＊지역번호?  ");
			regionId = sc.nextLine();
			Region tmp = this.dao.regionSearch("region", regionId);
			if(Objects.isNull(tmp)) {
				System.out.println();
				System.out.println("================================");
				System.out.println("            *경고*");
				System.out.println("지역번호 형식이 잘못 되었습니다.");
				System.out.println("================================");
			} else {
				break;
			}
		}
		
		this.mainMenu2_sub2_sub2();
		
		String departmentId = "";
		
		while(true) {
			System.out.println();
			System.out.print("＊부서번호?  ");
			departmentId = sc.nextLine();
			Department tmp = this.dao.departmentSearch("department", departmentId);
			if(Objects.isNull(tmp)) {
				System.out.println();
				System.out.println("================================");
				System.out.println("            *경고*");
				System.out.println("부서번호 형식이 잘못 되었습니다.");
				System.out.println("================================");
			} else {
				break;
			}
		}

		this.mainMenu2_sub3_sub2();
		
		String positionId = "";
		
		Position positionTmp = null;
		
		while(true) {
			
			System.out.println();
			System.out.print("＊직위번호?  ");
			positionId = sc.nextLine();
			
			positionTmp = this.dao.positionSearch("position", positionId);
			
			if(Objects.isNull(positionTmp)) {
				System.out.println();
				System.out.println("================================");
				System.out.println("            *경고*");
				System.out.println("직위번호 형식이 잘못 되었습니다.");
				System.out.println("================================");
			} else { 
				break;
			}
		}
		
		int basicPay = 0;
		
		while(true) {
			System.out.println();
			System.out.print("기본급 ?  ");
			basicPay = sc.nextInt();
			sc.nextLine();
			
			int minPay = positionTmp.getMinimumPay();
			
			if(basicPay < minPay) {
				System.out.println("================================");
				System.out.println("             *경고*");
				System.out.println("현재 " + positionTmp.getPositionName() +"의 최소 기본급은 " + minPay + "입니다.");
				System.out.println("다시 입력해주세요!");
				System.out.println("================================");
			} else {
				break;
			}
		}
		
			
		System.out.print("수당 ?  ");
		int extraPay = sc.nextInt();
		sc.nextLine();
		
		this.dao.employeeAdd(new Employee(this.dao.autoEmployeeID(), name, ssn, joinDate, callNum
				, regionId, departmentId, positionId, basicPay, extraPay));
		
		System.out.println("신규 직원 정보 입력 완료!");
		
	
	}

	// ===================================================================================

	// 직원 관리 v1.0 > 직원 관리 > 직원 전체 출력
	// 1.사번 정렬 2.이름 정렬 3.지역 정렬 4.부서 정렬 5.직위 정렬
	private void mainMenu1_sub2(Scanner sc) {
		System.out.println();

		while (true) {

			try {
				System.out.println("=================");
				System.out.println(" ★직원 관리 v1.0 > 직원 관리 > 직원 전체 출력");
				System.out.println("=================");

				System.out.println("1.사번 정렬 | 2.이름 정렬 | 3.지역 정렬 | 4.부서 정렬 | 5.직위 정렬");
				System.out.print("선택(1~5, 0 exit)?  ");

				int selectNum = 0;
				selectNum = sc.nextInt();
				sc.nextLine();

				if (selectNum == 0) {
					break;
				}

				switch (selectNum) {

				case 1:
					this.mainMenu1_sub2_sub1();
					break;
				case 2:
					this.mainMenu1_sub2_sub2();
					break;
				case 3:
					this.mainMenu1_sub2_sub3();
					break;
				case 4:
					this.mainMenu1_sub2_sub4();
					break;
				case 5:
					this.mainMenu1_sub2_sub5();
					break;
				}

			} catch (Exception e) {
				sc.nextLine();
				System.out.println("♨♨ 잘못된 접근 입니다.");
			}
			System.out.println();
		}

	}

	// 직원 관리 v1.0 > 직원 관리 > 직원 전체 출력 > 사번 정렬
	private void mainMenu1_sub2_sub1() {

		// 직원 정보 정렬 출력 액션 테스트
		// null 값이 없는 사본 배열 반환 받는다.
		Employee[] employeeList = dao.employeeList();
		// -> 사번 기준
		this.printEmployeeList(employeeList, "employeeId");

	}

	// 직원 관리 v1.0 > 직원 관리 > 직원 전체 출력 > 이름 정렬
	private void mainMenu1_sub2_sub2() {
		// 직원 정보 정렬 출력 액션 테스트
		// null 값이 없는 사본 배열 반환 받는다.
		Employee[] employeeList = dao.employeeList();
		// -> 이름 기준
		this.printEmployeeList(employeeList, "employeeName");
	}

	// 직원 관리 v1.0 > 직원 관리 > 직원 전체 출력 > 지역 정렬
	private void mainMenu1_sub2_sub3() {
		// -> 지역 기준
		Employee[] employeeList = dao.employeeList();
		this.printEmployeeList(employeeList, "region");
	}

	// 직원 관리 v1.0 > 직원 관리 > 직원 전체 출력 > 부서 정렬
	private void mainMenu1_sub2_sub4() {
		// -> 부서 기준
		Employee[] employeeList = dao.employeeList();
		this.printEmployeeList(employeeList, "department");
	}

	// 직원 관리 v1.0 > 직원 관리 > 직원 전체 출력 > 직위 정렬
	private void mainMenu1_sub2_sub5() {
		// -> 직위 기준
		Employee[] employeeList = dao.employeeList();
		this.printEmployeeList(employeeList, "position");
	}

	// ===================================================================================
	// 직원 관리 v1.0 > 직원 관리 > 직원 검색
	// 1.사번 정렬 2.이름 정렬 3.지역 정렬 4.부서 정렬 5.직위 정렬
	private void mainMenu1_sub3(Scanner sc) {
		System.out.println();

		while (true) {

			try {
				System.out.println("=================");
				System.out.println(" ★직원 관리 v1.0 > 직원 관리 > 직원 검색");
				System.out.println("=================");

				System.out.println("1.사번 기준 | 2.이름 기준 | 3.지역 기준 | 4.부서 기준 | 5.직위 기준");
				System.out.print("선택(1~5, 0 exit)?  ");

				int selectNum = 0;
				selectNum = sc.nextInt();
				sc.nextLine();

				if (selectNum == 0) {
					break;
				}

				switch (selectNum) {

				case 1:
					this.mainMenu1_sub3_sub1(sc);
					break;
				case 2:
					this.mainMenu1_sub3_sub2(sc);
					break;
				case 3:
					this.mainMenu1_sub3_sub3(sc);
					break;
				case 4:
					this.mainMenu1_sub3_sub4(sc);
					break;
				case 5:
					this.mainMenu1_sub3_sub5(sc);
					break;
				}

			} catch (Exception e) {
				sc.nextLine();
				System.out.println("♨♨ 잘못된 접근 입니다.");
			}
			System.out.println();
		}

	}

	// 직원 관리 v1.0 > 직원 관리 > 직원 검색 > 사번 기준
	private void mainMenu1_sub3_sub1(Scanner sc) {

		// 사번 질의
		System.out.print("검색하려는 사번?  ");

		String employeeId = "";
		employeeId = sc.nextLine();

		// 사번에 해당하는 직원에 대한 검색 진행 -> null 값 없는 사본 배열
		Employee[] employeeList = this.dao.employeeSearch("employeeId", employeeId);

		// 결과 출력 -> 출력 메소드 호출(사번 기준 정렬 포함)
		this.printEmployeeList(employeeList, "employeeId");

	}

	// 직원 관리 v1.0 > 직원 관리 > 직원 검색 > 이름 기준
	private void mainMenu1_sub3_sub2(Scanner sc) {

		// 이름 질의
		System.out.print("검색하려는 이름?  ");

		String employeeName = "";
		employeeName = sc.nextLine();

		// 이름에 해당하는 직원에 대한 검색 진행 -> null 값 없는 사본 배열
		Employee[] employeeList = this.dao.employeeSearch("employeeName", employeeName);

		// 결과 출력 -> 출력 메소드 호출(사번 기준 정렬 포함)
		this.printEmployeeList(employeeList, "employeeId");
	}

	// 직원 관리 v1.0 > 직원 관리 > 직원 검색 > 지역 기준
	private void mainMenu1_sub3_sub3(Scanner sc) {
		// 지역 질의
		System.out.print("검색하려는 지역?  ");

		String region = "";
		region = sc.nextLine();

		// 지역에 해당하는 직원에 대한 검색 진행 -> null 값 없는 사본 배열
		Employee[] employeeList = this.dao.employeeSearch("region", region);

		// 결과 출력 -> 출력 메소드 호출(사번 기준 정렬 포함)
		this.printEmployeeList(employeeList, "employeeId");
	}

	// 직원 관리 v1.0 > 직원 관리 > 직원 검색 > 부서 기준
	private void mainMenu1_sub3_sub4(Scanner sc) {
		// 이름 질의
		System.out.print("검색하려는 부서?  ");

		String department = "";
		department = sc.nextLine();

		// 이름에 해당하는 직원에 대한 검색 진행 -> null 값 없는 사본 배열
		Employee[] employeeList = this.dao.employeeSearch("department", department);

		// 결과 출력 -> 출력 메소드 호출(사번 기준 정렬 포함)
		this.printEmployeeList(employeeList, "employeeId");
	}

	// 직원 관리 v1.0 > 직원 관리 > 직원 검색 > 직위 기준
	private void mainMenu1_sub3_sub5(Scanner sc) {
		// 이름 질의
		System.out.print("검색하려는 직위?  ");

		String position = "";
		position = sc.nextLine();

		// 이름에 해당하는 직원에 대한 검색 진행 -> null 값 없는 사본 배열
		Employee[] employeeList = this.dao.employeeSearch("position", position);

		// 결과 출력 -> 출력 메소드 호출(사번 기준 정렬 포함)
		this.printEmployeeList(employeeList, "employeeId");
	}

	// ===================================================================================
	// 직원 관리 v1.0 > 기초 정보 관리
	// 1. 지역 관리 2. 부서 관리 3. 직위 관리
	public void mainMenu2(Scanner sc) {
		System.out.println();

		while (true) {

			try {
				System.out.println("=================");
				System.out.println(" ★직원 관리 v1.0 > 기초 정보 관리");
				System.out.println("=================");

				System.out.println("1. 지역 관리 | 2. 부서 관리 | 3. 직위 관리");
				System.out.print("선택(1~3, 0 exit)?  ");

				int selectNum = 0;
				selectNum = sc.nextInt();
				sc.nextLine();

				if (selectNum == 0) {
					break;
				}

				switch (selectNum) {

				case 1:
					this.mainMenu2_sub1(sc);
					break;
				case 2:
					this.mainMenu2_sub2(sc);
					break;
				case 3:
					this.mainMenu2_sub3(sc);
					break;
				}

			} catch (Exception e) {
				sc.nextLine();
				System.out.println("♨♨ 잘못된 접근 입니다.");
			}
			System.out.println();
		}
	}

	// 직원 관리 v1.0 > 기초 정보 관리 > 지역 관리
	// 1. 지역 입력 2. 지역 출력
	private void mainMenu2_sub1(Scanner sc) {
		System.out.println();

		while (true) {

			try {
				System.out.println("=================");
				System.out.println(" ★직원 관리 v1.0 > 기초 정보 관리 > 지역 관리");
				System.out.println("=================");

				System.out.println("1. 지역 입력 | 2. 지역 출력");
				System.out.print("선택(1~2, 0 exit)?  ");

				int selectNum = 0;
				selectNum = sc.nextInt();
				sc.nextLine();

				if (selectNum == 0) {
					break;
				}

				switch (selectNum) {

				case 1:
					this.mainMenu2_sub1_sub1(sc);
					break;
				case 2:
					this.mainMenu2_sub1_sub2();
					break;
				}

			} catch (Exception e) {
				sc.nextLine();
				System.out.println("♨♨ 잘못된 접근 입니다.");
			}
			System.out.println();
		}
	}

	// 직원 관리 v1.0 > 기초 정보 관리 > 지역 관리 > 지역 입력
	private void mainMenu2_sub1_sub1(Scanner sc) {

		// 기존 지역번호 / 지역명 출력
		this.mainMenu2_sub1_sub2();
		// 콘솔 입력을 통한 외부 입력
		// -> 신규 지역명
		System.out.print("신규 지역 이름?  ");
		String regionName = sc.nextLine();

		// 신규 지역을 저장소에 등록하는 메소드 호출
		this.dao.regionAdd(new Region(this.dao.autoRegionID(), regionName));

		System.out.println("신규 지역 정보 입력 완료!");
		System.out.println();

	}

	// 직원 관리 v1.0 > 기초 정보 관리 > 지역 관리 > 지역 출력
	private void mainMenu2_sub1_sub2() {
		System.out.println();
		System.out.println("--------------------");
		System.out.println("지역번호 지역이름");
		Region[] tmp = dao.regionList();
		for (Region r : tmp) {
			System.out.println(r);
		}
		System.out.println("--------------------");
		System.out.println();

	}

	// ===================================================================================
	// 직원 관리 v1.0 > 기초 정보 관리 > 부서 관리
	// 1. 부서 입력 2. 부서 출력
	private void mainMenu2_sub2(Scanner sc) {
		System.out.println();

		while (true) {

			try {
				System.out.println("=================");
				System.out.println(" ★직원 관리 v1.0 > 기초 정보 관리 > 부서 관리");
				System.out.println("=================");

				System.out.println("1. 부서 입력 | 2. 부서 출력");
				System.out.print("선택(1~2, 0 exit)?  ");

				int selectNum = 0;
				selectNum = sc.nextInt();
				sc.nextLine();

				if (selectNum == 0) {
					break;
				}

				switch (selectNum) {

				case 1:
					this.mainMenu2_sub2_sub1(sc);
					break;
				case 2:
					this.mainMenu2_sub2_sub2();
					break;
				}

			} catch (Exception e) {
				sc.nextLine();
				System.out.println("♨♨ 잘못된 접근 입니다.");
			}
			System.out.println();
		}
	}

	// 직원 관리 v1.0 > 기초 정보 관리 > 부서 관리 > 부서 입력
	private void mainMenu2_sub2_sub1(Scanner sc) {

		// 기존 부서번호 / 부서명 출력
		this.mainMenu2_sub2_sub2();
		// 콘솔 입력을 통한 외부 입력
		// -> 신규 부서명
		System.out.print("신규 부서 이름?  ");
		String departmentName = sc.nextLine();

		// 신규 부서를 저장소에 등록하는 메소드 호출
		this.dao.departmentAdd(new Department(this.dao.autoDepartmentID(), departmentName));

		System.out.println("신규 부서 정보 입력 완료!");
		System.out.println();

	}

	// 직원 관리 v1.0 > 기초 정보 관리 > 부서 관리 > 부서 출력
	private void mainMenu2_sub2_sub2() {
		System.out.println();
		System.out.println("--------------------");
		System.out.println("부서번호 부서명");
		Department[] tmp = dao.departmentList();
		for (Department d : tmp) {
			System.out.println(d);
		}
		System.out.println("--------------------");
		System.out.println();
	}

	// ===================================================================================
	// 직원 관리 v1.0 > 기초 정보 관리 > 직위 관리
	// 1. 직위 입력 2. 직위 출력
	private void mainMenu2_sub3(Scanner sc) {
		System.out.println();

		while (true) {

			try {
				System.out.println("=================");
				System.out.println(" ★직원 관리 v1.0 > 기초 정보 관리 > 직위 관리");
				System.out.println("=================");

				System.out.println("1. 직위 입력 | 2. 직위 출력");
				System.out.print("선택(1~2, 0 exit)?  ");

				int selectNum = 0;
				selectNum = sc.nextInt();
				sc.nextLine();

				if (selectNum == 0) {
					break;
				}

				switch (selectNum) {

				case 1:
					this.mainMenu2_sub3_sub1(sc);
					break;
				case 2:
					this.mainMenu2_sub3_sub2();
					break;
				}

			} catch (Exception e) {
				sc.nextLine();
				System.out.println("♨♨ 잘못된 접근 입니다.");
			}
			System.out.println();
		}
	}

	// 직원 관리 v1.0 > 기초 정보 관리 > 직위 관리 > 직위 입력
	private void mainMenu2_sub3_sub1(Scanner sc) {
		// 기존 직위번호 / 직위명 / 최소 기본급 출력
		this.mainMenu2_sub3_sub2();
		// 콘솔 입력을 통한 외부 입력
		// -> 신규 지역명
		System.out.print("신규 직위명 ?  ");
		String positionName = sc.nextLine();

		System.out.print("최소 기본급 ?  ");
		int minimumPay = sc.nextInt();
		sc.nextLine();

		// 신규 지역을 저장소에 등록하는 메소드 호출
		this.dao.positionAdd(new Position(this.dao.autoPositionID(), positionName, minimumPay));

		System.out.println("신규 직위 정보 입력 완료!");
		System.out.println();
	}

	// 직원 관리 v1.0 > 기초 정보 관리 > 직위 관리 > 직위 출력
	private void mainMenu2_sub3_sub2() {
		System.out.println();
		System.out.println("--------------------");
		System.out.println("직위번호 직위명 최소기본급");
		Position[] tmp = dao.positionList();
		for (Position p : tmp) {
			System.out.println(p);
		}
		System.out.println("--------------------");
		System.out.println();
	}

	// ===================================================================================

	// 직원 정보 출력 전용 메소드 구성 추가 -> 기준에 따른 정렬 출력 -> String key
	private void printEmployeeList(Employee[] employeeList, String key) {

		System.out.println();
		System.out.println("********************");
		// null 값이 없는 사본 배열이므로 크기를 확인하면 전체 인원 확인이 가능하다.
		System.out.println("전체 인원 : " + employeeList.length + "명");
		System.out.println("사번 / 이름 / 주민번호 / 입사일 / 전화번호 / 지역명 / 부서명 / 직위명 / 기본급 / 수당 / 급여");

		if (employeeList.length > 0) {

			// null값이 없는 배열을 가지고 사번 기준 정렬 액션 추가
			Arrays.sort(employeeList, new Comparator<Employee>() {

				@Override
				public int compare(Employee o1, Employee o2) {
					int temp = 0;

					if (key.equals("employeeId")) {
						temp = o1.getemployeeID().compareTo(o2.getemployeeID());
					} else if (key.equals("employeeName")) {
						temp = o1.getEmployeeName().compareTo(o2.getEmployeeName());
					} else if (key.equals("region")) {
						temp = dao.regionSearch("regionId", o1.getRegionID()).getRegionName()
								.compareTo(dao.regionSearch("regionId", o2.getRegionID()).getRegionName());
					} else if (key.equals("department")) {
						temp = dao.departmentSearch("departmentId", o1.getDepartmentID()).getDepartmentName().compareTo(
								dao.departmentSearch("departmentId", o2.getDepartmentID()).getDepartmentName());
					} else if (key.equals("position")) {
						temp = dao.positionSearch("positionId", o1.getPositionID()).getPositionName()
								.compareTo(dao.positionSearch("positionId", o2.getPositionID()).getPositionName());
					}
					return temp;
				}

			});
		} else {
			System.out.println("출력 대상이 없습니다.");
		}

		for (Employee e : employeeList) {
			System.out.printf("%s / %s / %s / %s / %s / %s / %s / %s / %,d / %,d / %,d%n", e.getemployeeID(),
					e.getEmployeeName(), e.getSsn(), e.getJoinDate(), e.getCallNum(),
					dao.regionSearch("regionId", e.getRegionID()).getRegionName(),
					dao.departmentSearch("departmentId", e.getDepartmentID()).getDepartmentName(),
					dao.positionSearch("positionId", e.getPositionID()).getPositionName(), e.getBasePay(),
					e.getExtraPay(), e.getPay());
		}

	}
}
