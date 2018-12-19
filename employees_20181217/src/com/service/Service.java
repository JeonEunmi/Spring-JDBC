package com.service;

import java.util.List;
import java.util.Scanner;

import com.domain.Department;
import com.domain.Employee;
import com.domain.Job;
import com.domain.Login;
import com.domain.Region;
import com.persistance.DepartmentDAO;
import com.persistance.JobDAO;
import com.persistance.LoginDAO;
import com.persistance.RegionDAO;

//메인 메뉴별 액션 클래스
public class Service {

	private String id_;

	// 의존주입 대상을 받을 때 인터페이스 자료형을 권장
	// ->타이어 인터페이스, 한국타이어 객체 or 금호타이어 객체
	// 의존주입 설정 필요
	// ->실제로는 RegionDAOImpl 객체가 전달될 예정이다
	private RegionDAO regionDAO;
	private DepartmentDAO departmentDAO;
	private JobDAO jobDAO;
	private LoginDAO loginDAO;

	public void setRegionDAO(RegionDAO regionDAO) {
		this.regionDAO = regionDAO;
	}

	public void setDepartmentDAO(DepartmentDAO departmentDAO) {
		this.departmentDAO = departmentDAO;
	}

	public void setJobDAO(JobDAO jobDAO) {
		this.jobDAO = jobDAO;
	}

	public void setLoginDAO(LoginDAO loginDAO) {
		this.loginDAO = loginDAO;
	}

	public void login(Scanner sc) {
		/*
		 * 아이디>admin 패스워드>1111 관리자 'admin' 로그인되었습니다.
		 */
		System.out.print("아이디>");
		String id_ = sc.nextLine();
		System.out.print("패스워드>");
		String pw_ = sc.nextLine();

		Login result = this.loginDAO.login(new Login(id_, pw_));
		if (result == null) {
			System.out.println("아이디 또는 패스워드가 틀렸습니다.");
		} else {
			System.out.printf("관리자 '%s' 로그인되었습니다.%n", id_);
			this.id_ = id_;
			this.main(sc);
		}

	}

	private void main(Scanner sc) {
		/*
		 * -------------- 직원관리 by SpringJDBC (관리자:admin) -------------- 1.직원관리 2.기초정보관리
		 * 3.관리자정보관리 선택>
		 */
		while (true) {
			System.out.println();
			System.out.println();
			System.out.println("--------------");
			System.out.printf("직원관리 by SpringJDBC (관리자:%s)%n", this.id_);
			System.out.println("--------------");
			System.out.println("1.직원관리  2.기초정보관리  3.관리자정보관리");
			System.out.print("선택(0/1)>");
			int m = sc.nextInt();
			sc.nextLine();

			if (m == 0) {
				System.out.printf("관리자 '%s' 로그아웃되었습니다.%n", this.id_);
				break;
			}
			switch (m) {
			case 1:
				this.menu1(sc);
				break;
			case 2:
				this.menu2(sc);
				break;
			case 3:
				this.menu3(sc);
				break;
			}
		}
	}

	private void menu1(Scanner sc) {
		while (true) {
			System.out.println();
			System.out.println();
			System.out.println("--------------");
			System.out.printf("직원관리 by SpringJDBC (관리자:%s) > 직원 관리%n", this.id_);
			System.out.println("--------------");
			System.out.println("1.직원입력  2.직원전체출력  3.직원검색  4.직원삭제");
			System.out.print("선택(0/1)>");
			int m = sc.nextInt();
			sc.nextLine();

			if (m == 0) {
				break;
			}
			switch (m) {
			case 1:
				this.menu1_sub1(sc);
				break;
			case 2:
				this.menu1_sub2(sc);
				break;
			case 3:
				this.menu1_sub3(sc);
				break;
			case 4:
				this.menu1_sub4(sc);
				break;
			}
		}
	}

	private void menu1_sub1(Scanner sc) {
		System.out.println();
		System.out.println();
		System.out.println("--------------");
		System.out.printf("직원관리 by SpringJDBC (관리자:%s) > 직원 관리 > 직원 입력%n", this.id_);
		System.out.println("--------------");
		System.out.print("이름>");
		String name = sc.nextLine();
		System.out.print("주민번호(YYMMDD-NNNNNNN)>");
		String ssn = sc.nextLine();
		System.out.print("입사일(YYYY-MM-DD)>");
		String hiredate = sc.nextLine();
		System.out.print("전화번호(010-XXXX-XXXX)>");
		String phone = sc.nextLine();
		this.regionPrint();
		System.out.print("지역번호>");
		String regId = sc.nextLine();
		this.departmentPrint();
		System.out.print("부서번호>");
		String deptId = sc.nextLine();
		this.jobPrint();
		System.out.print("직위번호>");
		String jobId = sc.nextLine();
		System.out.print("기본급>");
		int basicpay = sc.nextInt();
		sc.nextLine();
		System.out.print("수당>");
		int extrapay = sc.nextInt();
		sc.nextLine();

		/*
		 * int result = this.employeeDAO.add(new Employee(null, name, ssn,
		 * Date.valueOf(hiredate), phone, null, null, null, regId, deptId, jobId,
		 * basicpay, extrapay, (basicpay + extrapay))); if (result > 0) {
		 * System.out.println("신규 직원 정보 입력 완료!"); } else {
		 * System.out.println("신규 직원 정보 입력 실패!"); }
		 */

	}

	private void menu1_sub2(Scanner sc) {
		while (true) {
			System.out.println();
			System.out.println();
			System.out.println("--------------");
			System.out.printf("직원관리 by SpringJDBC (관리자:%s) > 직원 관리 > 직원 전체 출력%n", this.id_);
			System.out.println("--------------");
			System.out.println("1.사번정렬  2.이름정렬  3.지역명정렬  4.부서명정렬  5.직위명정렬");
			System.out.print("선택(0/1)>");
			int m = sc.nextInt();
			sc.nextLine();

			if (m == 0) {
				break;
			}
			switch (m) {
			case 1:
				this.menu1_sub2_sub1();
				break;
			case 2:
				this.menu1_sub2_sub2();
				break;
			case 3:
				this.menu1_sub2_sub3();
				break;
			case 4:
				this.menu1_sub2_sub4();
				break;
			case 5:
				this.menu1_sub2_sub5();
				break;
			}
		}
	}

	private void menu1_sub2_sub1() {
		System.out.println();
		System.out.println();
		System.out.println("--------------");
		System.out.printf("직원관리 by SpringJDBC (관리자:%s) > 직원관리 > 직원전체출력 > 사번정렬%n", this.id_);
		System.out.println("--------------");
		/*
		 * List<Employee> list = this.employeeDAO.list("empId");
		 * this.employeePrint(list);
		 */
	}

	private void menu1_sub2_sub2() {
		System.out.println();
		System.out.println();
		System.out.println("--------------");
		System.out.printf("직원관리 by SpringJDBC (관리자:%s) > 직원관리 > 직원전체출력 > 이름정렬%n", this.id_);
		System.out.println("--------------");
		/*
		 * List<Employee> list = this.employeeDAO.list("name_");
		 * this.employeePrint(list);
		 */
	}

	private void menu1_sub2_sub3() {
		System.out.println();
		System.out.println();
		System.out.println("--------------");
		System.out.printf("직원관리 by SpringJDBC (관리자:%s) > 직원관리 > 직원전체출력 > 지역명정렬%n", this.id_);
		System.out.println("--------------");
		/*
		 * List<Employee> list = this.employeeDAO.list("reg_name");
		 * this.employeePrint(list);
		 */
	}

	private void menu1_sub2_sub4() {
		System.out.println();
		System.out.println();
		System.out.println("--------------");
		System.out.printf("직원관리 by SpringJDBC (관리자:%s) > 직원관리 > 직원전체출력 > 부서명정렬%n", this.id_);
		System.out.println("--------------");
		/*
		 * List<Employee> list = this.employeeDAO.list("dept_name");
		 * this.employeePrint(list);
		 */
	}

	private void menu1_sub2_sub5() {
		System.out.println();
		System.out.println();
		System.out.println("--------------");
		System.out.printf("직원관리 by SpringJDBC (관리자:%s) > 직원관리 > 직원전체출력 > 직위명정렬%n", this.id_);
		System.out.println("--------------");
		/*
		 * List<Employee> list = this.employeeDAO.list("job_title");
		 * this.employeePrint(list);
		 */
	}

	private void menu1_sub3(Scanner sc) {
		while (true) {
			System.out.println();
			System.out.println();
			System.out.println("--------------");
			System.out.printf("직원관리 by SpringJDBC (관리자:%s) > 직원관리 > 직원검색%n", this.id_);
			System.out.println("--------------");
			System.out.println("1.사번기준 2.이름기준 3.지역명기준 4.부서명기준 5.직위명기준");
			System.out.print("선택>");

			int m = sc.nextInt();
			sc.nextLine();

			if (m == 0) {
				break;
			}
			switch (m) {
			case 1:
				this.menu1_sub3_sub1(sc);
				break;
			case 2:
				this.menu1_sub3_sub2(sc);
				break;
			case 3:
				this.menu1_sub3_sub3(sc);
				break;
			case 4:
				this.menu1_sub3_sub4(sc);
				break;
			case 5:
				this.menu1_sub3_sub5(sc);
				break;
			}
		}
	}

	private void menu1_sub3_sub1(Scanner sc) {

		System.out.print("사번>");
		String empId = sc.nextLine();
		/*
		 * List<Employee> list = this.employeeDAO.list("empId", empId); if (list.size()
		 * == 0) { System.out.println("검색 결과가 없습니다."); } else {
		 * this.employeePrint(list); }
		 */
	}

	private void menu1_sub3_sub2(Scanner sc) {

		System.out.print("이름>");
		String name_ = sc.nextLine();

		/*
		 * List<Employee> list = this.employeeDAO.list("name_", name_); if (list.size()
		 * == 0) { System.out.println("검색 결과가 없습니다."); } else {
		 * this.employeePrint(list); }
		 */
	}

	private void menu1_sub3_sub3(Scanner sc) {

		System.out.print("지역명>");
		String reg_name = sc.nextLine();

		/*
		 * List<Employee> list = this.employeeDAO.list("reg_name", reg_name); if
		 * (list.size() == 0) { System.out.println("검색 결과가 없습니다."); } else {
		 * this.employeePrint(list); }
		 */
	}

	private void menu1_sub3_sub4(Scanner sc) {

		System.out.print("부서명>");

		/*
		 * String dept_name = sc.nextLine(); List<Employee> list =
		 * this.employeeDAO.list("dept_name", dept_name); if (list.size() == 0) {
		 * System.out.println("검색 결과가 없습니다."); } else { this.employeePrint(list); }
		 */
	}

	private void menu1_sub3_sub5(Scanner sc) {

		System.out.print("직위명>");
		String job_title = sc.nextLine();

		/*
		 * List<Employee> list = this.employeeDAO.list("job_title", job_title); if
		 * (list.size() == 0) { System.out.println("검색 결과가 없습니다."); } else {
		 * this.employeePrint(list); }
		 */
	}

	private void menu1_sub4(Scanner sc) {

		System.out.print("사번>");
		String empId = sc.nextLine();
		/*
		 * List<Employee> list = this.employeeDAO.list("empId", empId); if (list.size()
		 * == 0) { System.out.println("검색 결과가 없습니다."); } else {
		 * this.employeePrint(list); System.out.print("현재 직원 정보를 삭제할까요(0/1)>"); int m =
		 * sc.nextInt(); sc.nextLine(); if (m == 1) { this.employeeDAO.remove(empId);
		 * System.out.printf("%s 직원이 삭제되었습니다.", empId); } }
		 */
	}

	private void menu2(Scanner sc) {
		/*
		 * -------------- 직원 관리 by SpringJDBC (관리자:admin) > 기초 정보 관리 --------------
		 * 1.지역관리 2.부서관리 3.직위관리 선택>
		 */
		while (true) {
			System.out.println();
			System.out.println();
			System.out.println("--------------");
			System.out.printf("직원관리 by SpringJDBC (관리자:%s) > 기초 정보 관리%n", this.id_);
			System.out.println("--------------");
			System.out.println("1.지역관리  2.부서관리  3.직위관리");
			System.out.print("선택>");
			int m = sc.nextInt();
			sc.nextLine();

			if (m == 0) {
				break;
			}
			switch (m) {
			case 1:
				this.menu2_sub1(sc);
				break;
			case 2:
				this.menu2_sub2(sc);
				break;
			case 3:
				this.menu2_sub3(sc);
				break;
			}
		}
	}

	private void menu2_sub1(Scanner sc) {
		/*
		 * -------------- 직원 관리 by SpringJDBC (관리자:admin) > 기초 정보 관리 > 지역 관리
		 * -------------- 1.지역입력 2.지역출력 3.지역삭제 선택>
		 */
		while (true) {
			System.out.println();
			System.out.println();
			System.out.println("--------------");
			System.out.printf("직원관리 by SpringJDBC (관리자:%s) > 기초 정보 관리 > 지역 관리%n", this.id_);
			System.out.println("--------------");
			System.out.println("1.지역입력  2.지역출력  3.지역삭제");
			System.out.print("선택>");
			int m = sc.nextInt();
			sc.nextLine();

			if (m == 0) {
				break;
			}
			switch (m) {
			case 1:
				this.menu2_sub1_sub1(sc);
				break;
			case 2:
				this.menu2_sub1_sub2();
				break;
			case 3:
				this.menu2_sub1_sub3(sc);
				break;
			}
		}

	}

	private void menu2_sub1_sub1(Scanner sc) {
		/*
		 * -------------- 직원 관리 by SpringJDBC (관리자:admin) > 기초 정보 관리 > 지역 관리 > 지역 입력
		 * -------------- 지역번호 / 지역명 REG01 / 강원 REG02 / 경기 REG03 / 경남 REG04 / 경북
		 * -------------- 신규 지역 이름>서울 신규 지역 정보 입력 완료!
		 */

		// 지역명 출력 전용 메소드
		this.regionPrint();

		System.out.print("신규 지역 이름>");
		String reg_name = sc.nextLine();

		int result = this.regionDAO.add(new Region(null, reg_name));
		if (result == 1) {
			System.out.println("신규 지역 입력 성공!");
		} else {
			System.out.println("신규 지역 입력 실패!");
		}

	}

	private void menu2_sub1_sub2() {
		/*
		 * -------------- 직원 관리 by SpringJDBC (관리자:admin) > 기초 정보 관리 > 지역 관리
		 * -------------- 1.지역입력 2.지역출력 3.지역삭제 선택>2 -------------- 지역번호 / 지역명 REG01 / 강원
		 * REG02 / 경기 REG03 / 경남 REG04 / 경북 REG05 / 서울 --------------
		 */

		// 지역명 출력 전용 메소드
		this.regionPrint();

	}

	private void menu2_sub1_sub3(Scanner sc) {
		/*
		 * -------------- 직원 관리 by SpringJDBC (관리자:admin) > 기초 정보 관리 > 지역 관리
		 * -------------- 1.지역입력 2.지역출력 3.지역삭제 선택>3 -------------- 지역번호 / 지역명 / 삭제가능여부
		 * REG01 / 강원 / X REG02 / 경기 / X REG03 / 경남 / X REG04 / 경북 / O REG05 / 서울 / X
		 * -------------- 지역번호>REG04 REG04 지역이 삭제되었습니다.
		 */
		List<Region> regions = this.regionDAO.list();
		System.out.println("-----------------");
		System.out.println("지역번호 / 지역명 / 삭제가능여부");
		for (Region r : regions) {
			System.out.println(r.print2());
		}
		System.out.println("-----------------");

		System.out.print("지역번호>");
		String regId = sc.nextLine();

		int result = this.regionDAO.remove(new Region(regId, null));
		if (result == 1) {
			System.out.printf("%s 지역 삭제 성공!%n", regId);
		} else {
			System.out.printf("%s 지역 삭제 실패!%n", regId);
		}

	}

	// 부서관리
	private void menu2_sub2(Scanner sc) {

		while (true) {
			System.out.println();
			System.out.println();
			System.out.println("--------------");
			System.out.printf("직원관리 by SpringJDBC (관리자:%s) > 기초 정보 관리 > 부서 관리%n", this.id_);
			System.out.println("--------------");
			System.out.println("1.부서입력  2.부서출력  3.부서삭제");
			System.out.print("선택>");
			int m = sc.nextInt();
			sc.nextLine();

			if (m == 0) {
				break;
			}
			switch (m) {
			case 1:
				this.menu2_sub2_sub1(sc);
				break;
			case 2:
				this.menu2_sub2_sub2();
				break;
			case 3:
				this.menu2_sub2_sub3(sc);
				break;
			}
		}

	}

	private void menu2_sub2_sub1(Scanner sc) {

		// 부서명 출력 전용 메소드
		this.departmentPrint();

		System.out.print("신규 부서 이름>");
		String dept_name = sc.nextLine();

		int result = this.departmentDAO.add(new Department(null, dept_name));
		if (result == 1) {
			System.out.println("신규 부서 입력 성공!");
		} else {
			System.out.println("신규 부서 입력 실패!");
		}

	}

	private void menu2_sub2_sub2() {

		// 부서명 출력 전용 메소드
		this.departmentPrint();

	}

	private void menu2_sub2_sub3(Scanner sc) {

		List<Department> departments = this.departmentDAO.list();
		System.out.println("-----------------");
		System.out.println("부서번호 / 부서명 / 삭제가능여부");
		for (Department d : departments) {
			System.out.println(d.print2());
		}
		System.out.println("-----------------");

		System.out.print("부서번호>");
		String depatId = sc.nextLine();

		int result = this.departmentDAO.remove(new Department(depatId, null));
		if (result == 1) {
			System.out.printf("%s 부서 삭제 성공!%n", depatId);
		} else {
			System.out.printf("%s 부서 삭제 실패!%n", depatId);
		}

	}

	private void menu2_sub3(Scanner sc) {

		while (true) {
			System.out.println();
			System.out.println();
			System.out.println("--------------");
			System.out.printf("직원관리 by SpringJDBC (관리자:%s) > 기초 정보 관리 > 직위 관리%n", this.id_);
			System.out.println("--------------");
			System.out.println("1.직위입력  2.직위출력  3.직위삭제");
			System.out.print("선택>");
			int m = sc.nextInt();
			sc.nextLine();

			if (m == 0) {
				break;
			}
			switch (m) {
			case 1:
				this.menu2_sub3_sub1(sc);
				break;
			case 2:
				this.menu2_sub3_sub2();
				break;
			case 3:
				this.menu2_sub3_sub3(sc);
				break;
			}
		}

	}

	private void menu2_sub3_sub1(Scanner sc) {

		// 직위명 출력 전용 메소드
		this.jobPrint();

		System.out.print("신규 직위 이름>");
		String job_title = sc.nextLine();

		int result = this.jobDAO.add(new Job(null, job_title));
		if (result == 1) {
			System.out.println("신규 직위 입력 성공!");
		} else {
			System.out.println("신규 직위 입력 실패!");
		}

	}

	private void menu2_sub3_sub2() {

		// 직위명 출력 전용 메소드
		this.jobPrint();

	}

	private void menu2_sub3_sub3(Scanner sc) {

		List<Job> jobs = this.jobDAO.list();
		System.out.println("-----------------");
		System.out.println("직위번호 / 직위명 / 삭제가능여부");
		for (Job r : jobs) {
			System.out.println(r.print2());
		}
		System.out.println("-----------------");

		System.out.print("직위번호>");
		String jobId = sc.nextLine();

		int result = this.jobDAO.remove(new Job(jobId, null));
		if (result == 1) {
			System.out.printf("%s 지역 삭제 성공!%n", jobId);
		} else {
			System.out.printf("%s 지역 삭제 실패!%n", jobId);
		}

	}

	private void menu3(Scanner sc) {
		/*
		 * -------------- 직원 관리 by SpringJDBC (관리자:admin) > 관리자정보관리 --------------
		 * 1.관리자추가 2.패스워드변경 선택>
		 */

		while (true) {
			System.out.println();
			System.out.println();
			System.out.println("--------------");
			System.out.printf("직원 관리 by SpringJDBC (관리자:%s) > 관리자정보관리%n", this.id_);
			System.out.println("--------------");
			System.out.println("1.관리자추가 2.패스워드변경");
			System.out.print("선택>");

			int m = sc.nextInt();
			sc.nextLine();

			if (m == 0)
				break;

			switch (m) {
			case 1:
				this.menu3_sub1(sc);
				break;
			case 2:
				this.menu3_sub2(sc);
				break;
			}
		}

	}

	private void menu3_sub1(Scanner sc) {
		/*
		 * -------------- 직원 관리 by SpringJDBC (관리자:admin) > 관리자정보관리 --------------
		 * 1.관리자추가 2.패스워드변경 선택>1 아이디>hong 패스워드>1234 신규관리자 'hong'이(가) 추가되었습니다.
		 */

		System.out.print("아이디>");
		String adminId = sc.nextLine();
		System.out.print("패스워드>");
		String password = sc.nextLine();

		int result = this.loginDAO.add(new Login(adminId, password));
		if (result > 0) {
			System.out.printf("신규관리자 '%s'가 생성되었습니다.", adminId);
		} else {
			System.out.println("신규관리자 추가 실패!");
		}

	}

	private void menu3_sub2(Scanner sc) {
		/*
		 * -------------- 직원 관리 by SpringJDBC (관리자:admin) > 관리자정보관리 --------------
		 * 1.관리자추가 2.패스워드변경 선택>2 현재패스워드>1111 신규패스워드>1234 패스워드를 변경할까요(0/1)>1 관리자 'admin'의
		 * 패스워드가 변경되었습니다.
		 */

		System.out.print("현재패스워드>");
		String password = sc.nextLine();
		System.out.print("신규패스워드>");
		String newPassword = sc.nextLine();

		/*
		 * int result = this.loginDAO.modify(new Login(this.id_, password,
		 * newPassword)); if (result > 0) {
		 * System.out.printf("관리자 '%s'의 패스워드가 변경되었습니다.", this.id_); } else {
		 * System.out.println("패스워드 변경 실패!"); }
		 * 
		 */
	}

	// 지역명 출력 전용 메소드
	private void regionPrint() {
		List<Region> regions = this.regionDAO.list();
		System.out.println("-----------------");
		System.out.println("지역번호 / 지역명");
		for (Region r : regions) {
			System.out.println(r.print1());
		}
		System.out.println("-----------------");
	}

	// 부서명 출력 전용 메소드
	private void departmentPrint() {

		List<Department> departments = this.departmentDAO.list();
		System.out.println("-----------------");
		System.out.println("부서번호 / 부서명");
		for (Department d : departments) {
			System.out.println(d.print1());
		}
		System.out.println("-----------------");

	}

	// 직위명 출력 전용 메소드
	private void jobPrint() {

		List<Job> jobs = this.jobDAO.list();
		System.out.println("-----------------");
		System.out.println("지역번호 / 지역명");
		for (Job j : jobs) {
			System.out.println(j.print1());
		}
		System.out.println("-----------------");

	}

	// 직원정보 출력 전용 메소드
	private void employeePrint(List<Employee> list) {

		/*
		 * -------------- 출력 인원: 62명 -------------- 사번 / 이름 / 주민번호 / 입사일 / 전화번호 / 지역명 /
		 * 부서명 / 직위명 / 기본급 / 수당 / 급여 EMP001 / 홍길동 / 771212-1022432 / 1998-10-11 /
		 * 011-2356-4528 / 서울 / 기획부 / 부장 / 2,610,000 / 200,000 / 2,810,000 EMP002 / 이순신
		 * / 801007-1544236 / 2000-11-29 / 010-4758-6532 / 경기 / 총무부 / 사원 / 1,320,000 /
		 * 200,000 / 1,520,000 ...
		 */
		/*
		 * System.out.printf("출력 인원: %d명%n", list.size());
		 * System.out.println("--------------"); System.out.
		 * println("사번 / 이름 / 주민번호 / 입사일 / 전화번호 / 지역명 / 부서명 / 직위명 / 기본급 / 수당 / 급여"); for
		 * (Employee emp : list) { System.out.println(emp.toString()); }
		 */
	}

}
