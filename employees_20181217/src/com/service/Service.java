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

//���� �޴��� �׼� Ŭ����
public class Service {

	private String id_;

	// �������� ����� ���� �� �������̽� �ڷ����� ����
	// ->Ÿ�̾� �������̽�, �ѱ�Ÿ�̾� ��ü or ��ȣŸ�̾� ��ü
	// �������� ���� �ʿ�
	// ->�����δ� RegionDAOImpl ��ü�� ���޵� �����̴�
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
		 * ���̵�>admin �н�����>1111 ������ 'admin' �α��εǾ����ϴ�.
		 */
		System.out.print("���̵�>");
		String id_ = sc.nextLine();
		System.out.print("�н�����>");
		String pw_ = sc.nextLine();

		Login result = this.loginDAO.login(new Login(id_, pw_));
		if (result == null) {
			System.out.println("���̵� �Ǵ� �н����尡 Ʋ�Ƚ��ϴ�.");
		} else {
			System.out.printf("������ '%s' �α��εǾ����ϴ�.%n", id_);
			this.id_ = id_;
			this.main(sc);
		}

	}

	private void main(Scanner sc) {
		/*
		 * -------------- �������� by SpringJDBC (������:admin) -------------- 1.�������� 2.������������
		 * 3.�������������� ����>
		 */
		while (true) {
			System.out.println();
			System.out.println();
			System.out.println("--------------");
			System.out.printf("�������� by SpringJDBC (������:%s)%n", this.id_);
			System.out.println("--------------");
			System.out.println("1.��������  2.������������  3.��������������");
			System.out.print("����(0/1)>");
			int m = sc.nextInt();
			sc.nextLine();

			if (m == 0) {
				System.out.printf("������ '%s' �α׾ƿ��Ǿ����ϴ�.%n", this.id_);
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
			System.out.printf("�������� by SpringJDBC (������:%s) > ���� ����%n", this.id_);
			System.out.println("--------------");
			System.out.println("1.�����Է�  2.������ü���  3.�����˻�  4.��������");
			System.out.print("����(0/1)>");
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
		System.out.printf("�������� by SpringJDBC (������:%s) > ���� ���� > ���� �Է�%n", this.id_);
		System.out.println("--------------");
		System.out.print("�̸�>");
		String name = sc.nextLine();
		System.out.print("�ֹι�ȣ(YYMMDD-NNNNNNN)>");
		String ssn = sc.nextLine();
		System.out.print("�Ի���(YYYY-MM-DD)>");
		String hiredate = sc.nextLine();
		System.out.print("��ȭ��ȣ(010-XXXX-XXXX)>");
		String phone = sc.nextLine();
		this.regionPrint();
		System.out.print("������ȣ>");
		String regId = sc.nextLine();
		this.departmentPrint();
		System.out.print("�μ���ȣ>");
		String deptId = sc.nextLine();
		this.jobPrint();
		System.out.print("������ȣ>");
		String jobId = sc.nextLine();
		System.out.print("�⺻��>");
		int basicpay = sc.nextInt();
		sc.nextLine();
		System.out.print("����>");
		int extrapay = sc.nextInt();
		sc.nextLine();

		/*
		 * int result = this.employeeDAO.add(new Employee(null, name, ssn,
		 * Date.valueOf(hiredate), phone, null, null, null, regId, deptId, jobId,
		 * basicpay, extrapay, (basicpay + extrapay))); if (result > 0) {
		 * System.out.println("�ű� ���� ���� �Է� �Ϸ�!"); } else {
		 * System.out.println("�ű� ���� ���� �Է� ����!"); }
		 */

	}

	private void menu1_sub2(Scanner sc) {
		while (true) {
			System.out.println();
			System.out.println();
			System.out.println("--------------");
			System.out.printf("�������� by SpringJDBC (������:%s) > ���� ���� > ���� ��ü ���%n", this.id_);
			System.out.println("--------------");
			System.out.println("1.�������  2.�̸�����  3.����������  4.�μ�������  5.����������");
			System.out.print("����(0/1)>");
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
		System.out.printf("�������� by SpringJDBC (������:%s) > �������� > ������ü��� > �������%n", this.id_);
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
		System.out.printf("�������� by SpringJDBC (������:%s) > �������� > ������ü��� > �̸�����%n", this.id_);
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
		System.out.printf("�������� by SpringJDBC (������:%s) > �������� > ������ü��� > ����������%n", this.id_);
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
		System.out.printf("�������� by SpringJDBC (������:%s) > �������� > ������ü��� > �μ�������%n", this.id_);
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
		System.out.printf("�������� by SpringJDBC (������:%s) > �������� > ������ü��� > ����������%n", this.id_);
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
			System.out.printf("�������� by SpringJDBC (������:%s) > �������� > �����˻�%n", this.id_);
			System.out.println("--------------");
			System.out.println("1.������� 2.�̸����� 3.��������� 4.�μ������ 5.���������");
			System.out.print("����>");

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

		System.out.print("���>");
		String empId = sc.nextLine();
		/*
		 * List<Employee> list = this.employeeDAO.list("empId", empId); if (list.size()
		 * == 0) { System.out.println("�˻� ����� �����ϴ�."); } else {
		 * this.employeePrint(list); }
		 */
	}

	private void menu1_sub3_sub2(Scanner sc) {

		System.out.print("�̸�>");
		String name_ = sc.nextLine();

		/*
		 * List<Employee> list = this.employeeDAO.list("name_", name_); if (list.size()
		 * == 0) { System.out.println("�˻� ����� �����ϴ�."); } else {
		 * this.employeePrint(list); }
		 */
	}

	private void menu1_sub3_sub3(Scanner sc) {

		System.out.print("������>");
		String reg_name = sc.nextLine();

		/*
		 * List<Employee> list = this.employeeDAO.list("reg_name", reg_name); if
		 * (list.size() == 0) { System.out.println("�˻� ����� �����ϴ�."); } else {
		 * this.employeePrint(list); }
		 */
	}

	private void menu1_sub3_sub4(Scanner sc) {

		System.out.print("�μ���>");

		/*
		 * String dept_name = sc.nextLine(); List<Employee> list =
		 * this.employeeDAO.list("dept_name", dept_name); if (list.size() == 0) {
		 * System.out.println("�˻� ����� �����ϴ�."); } else { this.employeePrint(list); }
		 */
	}

	private void menu1_sub3_sub5(Scanner sc) {

		System.out.print("������>");
		String job_title = sc.nextLine();

		/*
		 * List<Employee> list = this.employeeDAO.list("job_title", job_title); if
		 * (list.size() == 0) { System.out.println("�˻� ����� �����ϴ�."); } else {
		 * this.employeePrint(list); }
		 */
	}

	private void menu1_sub4(Scanner sc) {

		System.out.print("���>");
		String empId = sc.nextLine();
		/*
		 * List<Employee> list = this.employeeDAO.list("empId", empId); if (list.size()
		 * == 0) { System.out.println("�˻� ����� �����ϴ�."); } else {
		 * this.employeePrint(list); System.out.print("���� ���� ������ �����ұ��(0/1)>"); int m =
		 * sc.nextInt(); sc.nextLine(); if (m == 1) { this.employeeDAO.remove(empId);
		 * System.out.printf("%s ������ �����Ǿ����ϴ�.", empId); } }
		 */
	}

	private void menu2(Scanner sc) {
		/*
		 * -------------- ���� ���� by SpringJDBC (������:admin) > ���� ���� ���� --------------
		 * 1.�������� 2.�μ����� 3.�������� ����>
		 */
		while (true) {
			System.out.println();
			System.out.println();
			System.out.println("--------------");
			System.out.printf("�������� by SpringJDBC (������:%s) > ���� ���� ����%n", this.id_);
			System.out.println("--------------");
			System.out.println("1.��������  2.�μ�����  3.��������");
			System.out.print("����>");
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
		 * -------------- ���� ���� by SpringJDBC (������:admin) > ���� ���� ���� > ���� ����
		 * -------------- 1.�����Է� 2.������� 3.�������� ����>
		 */
		while (true) {
			System.out.println();
			System.out.println();
			System.out.println("--------------");
			System.out.printf("�������� by SpringJDBC (������:%s) > ���� ���� ���� > ���� ����%n", this.id_);
			System.out.println("--------------");
			System.out.println("1.�����Է�  2.�������  3.��������");
			System.out.print("����>");
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
		 * -------------- ���� ���� by SpringJDBC (������:admin) > ���� ���� ���� > ���� ���� > ���� �Է�
		 * -------------- ������ȣ / ������ REG01 / ���� REG02 / ��� REG03 / �泲 REG04 / ���
		 * -------------- �ű� ���� �̸�>���� �ű� ���� ���� �Է� �Ϸ�!
		 */

		// ������ ��� ���� �޼ҵ�
		this.regionPrint();

		System.out.print("�ű� ���� �̸�>");
		String reg_name = sc.nextLine();

		int result = this.regionDAO.add(new Region(null, reg_name));
		if (result == 1) {
			System.out.println("�ű� ���� �Է� ����!");
		} else {
			System.out.println("�ű� ���� �Է� ����!");
		}

	}

	private void menu2_sub1_sub2() {
		/*
		 * -------------- ���� ���� by SpringJDBC (������:admin) > ���� ���� ���� > ���� ����
		 * -------------- 1.�����Է� 2.������� 3.�������� ����>2 -------------- ������ȣ / ������ REG01 / ����
		 * REG02 / ��� REG03 / �泲 REG04 / ��� REG05 / ���� --------------
		 */

		// ������ ��� ���� �޼ҵ�
		this.regionPrint();

	}

	private void menu2_sub1_sub3(Scanner sc) {
		/*
		 * -------------- ���� ���� by SpringJDBC (������:admin) > ���� ���� ���� > ���� ����
		 * -------------- 1.�����Է� 2.������� 3.�������� ����>3 -------------- ������ȣ / ������ / �������ɿ���
		 * REG01 / ���� / X REG02 / ��� / X REG03 / �泲 / X REG04 / ��� / O REG05 / ���� / X
		 * -------------- ������ȣ>REG04 REG04 ������ �����Ǿ����ϴ�.
		 */
		List<Region> regions = this.regionDAO.list();
		System.out.println("-----------------");
		System.out.println("������ȣ / ������ / �������ɿ���");
		for (Region r : regions) {
			System.out.println(r.print2());
		}
		System.out.println("-----------------");

		System.out.print("������ȣ>");
		String regId = sc.nextLine();

		int result = this.regionDAO.remove(new Region(regId, null));
		if (result == 1) {
			System.out.printf("%s ���� ���� ����!%n", regId);
		} else {
			System.out.printf("%s ���� ���� ����!%n", regId);
		}

	}

	// �μ�����
	private void menu2_sub2(Scanner sc) {

		while (true) {
			System.out.println();
			System.out.println();
			System.out.println("--------------");
			System.out.printf("�������� by SpringJDBC (������:%s) > ���� ���� ���� > �μ� ����%n", this.id_);
			System.out.println("--------------");
			System.out.println("1.�μ��Է�  2.�μ����  3.�μ�����");
			System.out.print("����>");
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

		// �μ��� ��� ���� �޼ҵ�
		this.departmentPrint();

		System.out.print("�ű� �μ� �̸�>");
		String dept_name = sc.nextLine();

		int result = this.departmentDAO.add(new Department(null, dept_name));
		if (result == 1) {
			System.out.println("�ű� �μ� �Է� ����!");
		} else {
			System.out.println("�ű� �μ� �Է� ����!");
		}

	}

	private void menu2_sub2_sub2() {

		// �μ��� ��� ���� �޼ҵ�
		this.departmentPrint();

	}

	private void menu2_sub2_sub3(Scanner sc) {

		List<Department> departments = this.departmentDAO.list();
		System.out.println("-----------------");
		System.out.println("�μ���ȣ / �μ��� / �������ɿ���");
		for (Department d : departments) {
			System.out.println(d.print2());
		}
		System.out.println("-----------------");

		System.out.print("�μ���ȣ>");
		String depatId = sc.nextLine();

		int result = this.departmentDAO.remove(new Department(depatId, null));
		if (result == 1) {
			System.out.printf("%s �μ� ���� ����!%n", depatId);
		} else {
			System.out.printf("%s �μ� ���� ����!%n", depatId);
		}

	}

	private void menu2_sub3(Scanner sc) {

		while (true) {
			System.out.println();
			System.out.println();
			System.out.println("--------------");
			System.out.printf("�������� by SpringJDBC (������:%s) > ���� ���� ���� > ���� ����%n", this.id_);
			System.out.println("--------------");
			System.out.println("1.�����Է�  2.�������  3.��������");
			System.out.print("����>");
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

		// ������ ��� ���� �޼ҵ�
		this.jobPrint();

		System.out.print("�ű� ���� �̸�>");
		String job_title = sc.nextLine();

		int result = this.jobDAO.add(new Job(null, job_title));
		if (result == 1) {
			System.out.println("�ű� ���� �Է� ����!");
		} else {
			System.out.println("�ű� ���� �Է� ����!");
		}

	}

	private void menu2_sub3_sub2() {

		// ������ ��� ���� �޼ҵ�
		this.jobPrint();

	}

	private void menu2_sub3_sub3(Scanner sc) {

		List<Job> jobs = this.jobDAO.list();
		System.out.println("-----------------");
		System.out.println("������ȣ / ������ / �������ɿ���");
		for (Job r : jobs) {
			System.out.println(r.print2());
		}
		System.out.println("-----------------");

		System.out.print("������ȣ>");
		String jobId = sc.nextLine();

		int result = this.jobDAO.remove(new Job(jobId, null));
		if (result == 1) {
			System.out.printf("%s ���� ���� ����!%n", jobId);
		} else {
			System.out.printf("%s ���� ���� ����!%n", jobId);
		}

	}

	private void menu3(Scanner sc) {
		/*
		 * -------------- ���� ���� by SpringJDBC (������:admin) > �������������� --------------
		 * 1.�������߰� 2.�н����庯�� ����>
		 */

		while (true) {
			System.out.println();
			System.out.println();
			System.out.println("--------------");
			System.out.printf("���� ���� by SpringJDBC (������:%s) > ��������������%n", this.id_);
			System.out.println("--------------");
			System.out.println("1.�������߰� 2.�н����庯��");
			System.out.print("����>");

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
		 * -------------- ���� ���� by SpringJDBC (������:admin) > �������������� --------------
		 * 1.�������߰� 2.�н����庯�� ����>1 ���̵�>hong �н�����>1234 �ű԰����� 'hong'��(��) �߰��Ǿ����ϴ�.
		 */

		System.out.print("���̵�>");
		String adminId = sc.nextLine();
		System.out.print("�н�����>");
		String password = sc.nextLine();

		int result = this.loginDAO.add(new Login(adminId, password));
		if (result > 0) {
			System.out.printf("�ű԰����� '%s'�� �����Ǿ����ϴ�.", adminId);
		} else {
			System.out.println("�ű԰����� �߰� ����!");
		}

	}

	private void menu3_sub2(Scanner sc) {
		/*
		 * -------------- ���� ���� by SpringJDBC (������:admin) > �������������� --------------
		 * 1.�������߰� 2.�н����庯�� ����>2 �����н�����>1111 �ű��н�����>1234 �н����带 �����ұ��(0/1)>1 ������ 'admin'��
		 * �н����尡 ����Ǿ����ϴ�.
		 */

		System.out.print("�����н�����>");
		String password = sc.nextLine();
		System.out.print("�ű��н�����>");
		String newPassword = sc.nextLine();

		/*
		 * int result = this.loginDAO.modify(new Login(this.id_, password,
		 * newPassword)); if (result > 0) {
		 * System.out.printf("������ '%s'�� �н����尡 ����Ǿ����ϴ�.", this.id_); } else {
		 * System.out.println("�н����� ���� ����!"); }
		 * 
		 */
	}

	// ������ ��� ���� �޼ҵ�
	private void regionPrint() {
		List<Region> regions = this.regionDAO.list();
		System.out.println("-----------------");
		System.out.println("������ȣ / ������");
		for (Region r : regions) {
			System.out.println(r.print1());
		}
		System.out.println("-----------------");
	}

	// �μ��� ��� ���� �޼ҵ�
	private void departmentPrint() {

		List<Department> departments = this.departmentDAO.list();
		System.out.println("-----------------");
		System.out.println("�μ���ȣ / �μ���");
		for (Department d : departments) {
			System.out.println(d.print1());
		}
		System.out.println("-----------------");

	}

	// ������ ��� ���� �޼ҵ�
	private void jobPrint() {

		List<Job> jobs = this.jobDAO.list();
		System.out.println("-----------------");
		System.out.println("������ȣ / ������");
		for (Job j : jobs) {
			System.out.println(j.print1());
		}
		System.out.println("-----------------");

	}

	// �������� ��� ���� �޼ҵ�
	private void employeePrint(List<Employee> list) {

		/*
		 * -------------- ��� �ο�: 62�� -------------- ��� / �̸� / �ֹι�ȣ / �Ի��� / ��ȭ��ȣ / ������ /
		 * �μ��� / ������ / �⺻�� / ���� / �޿� EMP001 / ȫ�浿 / 771212-1022432 / 1998-10-11 /
		 * 011-2356-4528 / ���� / ��ȹ�� / ���� / 2,610,000 / 200,000 / 2,810,000 EMP002 / �̼���
		 * / 801007-1544236 / 2000-11-29 / 010-4758-6532 / ��� / �ѹ��� / ��� / 1,320,000 /
		 * 200,000 / 1,520,000 ...
		 */
		/*
		 * System.out.printf("��� �ο�: %d��%n", list.size());
		 * System.out.println("--------------"); System.out.
		 * println("��� / �̸� / �ֹι�ȣ / �Ի��� / ��ȭ��ȣ / ������ / �μ��� / ������ / �⺻�� / ���� / �޿�"); for
		 * (Employee emp : list) { System.out.println(emp.toString()); }
		 */
	}

}
