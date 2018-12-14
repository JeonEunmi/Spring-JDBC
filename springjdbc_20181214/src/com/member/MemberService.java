package com.member;

import java.util.List;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class MemberService {

	// ������ �����̳� �غ�
	ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");

	// ��ü ��û
	MemberJDBCTemplate memberJDBCTemplate = (MemberJDBCTemplate) context.getBean("memberJDBCTemplate");

	// 1.ȸ�������Է�
	public void menu1(Scanner sc) {
		System.out.println("�ű� ȸ�� ������ �Է��ϴ� �����Դϴ�.");
		// ȸ�����̵� -> �ڵ� ����
		// �̸�, ��ȭ��ȣ, �̸��� -> �ܺ� �Է�
		// ����� -> �ý����� ���� ��¥
		System.out.print("�̸�>");
		String name = sc.nextLine();
		System.out.print("��ȭ��ȣ>");
		String phone = sc.nextLine();
		System.out.print("�̸���>");
		String email = sc.nextLine();
		Member temp = new Member(null, name, phone, email, null);

		System.out.printf("�Է��Ͻ� ������ %n �̸� : %s , ��ȭ��ȣ : %s , �̸��� : %s �Դϴ�. %n", name, phone, email);
		System.out.print("�ű� ȸ�� ������ ����ұ��? (0/1) > ");
		int select = sc.nextInt();
		sc.nextLine();

		if (select == 1) {
			int result = this.memberJDBCTemplate.add(temp);
			System.out.printf("%s �� ��(��) ���ԵǾ����ϴ�.%n", result);
		} else if (select == 0) {
			System.out.println("ȸ������� ��ҵǾ����ϴ�.");
		} else {
			System.out.println("�߸� �����̽��ϴ�. �ٽ� �Է����ּ���.");
		}
	}

	// 2.ȸ����������
	public void menu2(Scanner sc) {
		System.out.println();
		System.out.println("ȸ�����̵� / �̸� / ��ȭ��ȣ / �̸��� / �����");
		System.out.println("-------------------------------------------------------");
		List<Member> list = this.memberJDBCTemplate.memberList();
		for (Member m : list) {
			System.out.println(m.toString());
		}
		System.out.println("-------------------------------------------------------");
		System.out.printf("�� %s��%n", list.size());
		System.out.println();
		System.out.print("������ ȸ�� ���̵� �Է��ϼ���.>");
		String mid = sc.nextLine();
		
		System.out.printf("�����Ͻ� ȸ�� ���̵�� %s �Դϴ�. %n", mid);
		System.out.print("ȸ�� ������ �����ұ��? (0/1) >");
		int select = sc.nextInt();
		sc.nextLine();
		
		if (select == 1) {
			int result = this.memberJDBCTemplate.remove(mid);
			System.out.printf("%s �� ��(��) �����Ǿ����ϴ�.%n", result);
		} else if (select == 0) {
			System.out.println("ȸ������� ��ҵǾ����ϴ�.");
		} else {
			System.out.println("�߸� �����̽��ϴ�. �ٽ� �Է����ּ���.");
		}
	}
	
	// 3.ȸ��������ü���
	public void menu3() {
		System.out.println();
		System.out.println("ȸ�����̵� / �̸� / ��ȭ��ȣ / �̸��� / �����");
		System.out.println("-------------------------------------------------------");
		List<Member> list = this.memberJDBCTemplate.memberList();
		for (Member m : list) {
			System.out.println(m.toString());
		}
		System.out.println("-------------------------------------------------------");
		System.out.printf("�� %s��%n", list.size());
	}
	
	// 4.ȸ�������˻�
	public void menu4(Scanner sc) {
		System.out.println("");
		System.out.println("1. ȸ�����̵�  2.�̸�  3.��ȭ��ȣ  4.�̸���  5.�����");
		System.out.print("�˻� ������ �����ϼ���.> ");
		int select = sc.nextInt();
		sc.nextLine();
		
		System.out.print("�˻� �ܾ �Է��ϼ���.> ");
		
		String value = sc.nextLine();
		
		String key = "";
		
		if(select == 1) {
			key = "mid_";
		} else if(select == 2) {
			key = "name_";
		} else if(select == 3){
			key = "phone";
		} else if(select == 4) {
			key = "email";
		} else if(select == 5) {
			key = "regDate";
		}
		
		System.out.println("ȸ�����̵� / �̸� / ��ȭ��ȣ / �̸��� / �����");
		System.out.println("-------------------------------------------------------");
		List<Member> list = this.memberJDBCTemplate.memberSearch(key, value);
		for (Member m : list) {
			System.out.println(m.toString());
		}
		System.out.println("-------------------------------------------------------");
		System.out.printf("�� %s��%n", list.size());
	}

}
