package com.member.instructorver;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;

public class MemberService {

	// @Autowired ������̼� ���� �߰�
	// �ʵ� ������ ���� �������� ����
	// ->�ʵ� �ʱ�ȭ ����
	// ->�ܺ�(������)���� ���࿡ �ʿ��� �ڷ�(��ü)���� �����Ѵ�
	// ->��������(Dependency Injection, DI)
	// ����) byType ������� �۵���
	//�������̽� �ڷ��� ������ �����Ѵ�.
	@Autowired
	private MemberDAO dao;
	
	//1.ȸ�������Է�
	public void menu1(Scanner sc) {
		System.out.println("�ű� ȸ�� ������ �Է��ϴ� �����Դϴ�.");
		//ȸ�����̵� -> �ڵ� ����
		//�̸�, ��ȭ��ȣ, �̸��� -> �ܺ� �Է�
		//����� -> �ý����� ���� ��¥ 
		System.out.print("�̸�>");
		String name = sc.nextLine();
		System.out.print("��ȭ��ȣ>");
		String phone = sc.nextLine();
		System.out.print("�̸���>");
		String email = sc.nextLine();
		System.out.println();
		System.out.printf("�̸�:%s, ��ȭ��ȣ:%s, �̸���:%s%n", name, phone, email);
		System.out.print("�ű� ȸ�� ������ �Է��ұ��(0/1)>");
		int m = sc.nextInt();
		sc.nextLine();
		if (m == 1) {
			Member temp = new Member(null, name, phone, email, null);
			this.dao.memberAdd(temp);
			System.out.printf("�ű� ȸ�� ������ �ԷµǾ����ϴ�%n");
		} else {
			System.out.printf("�ű� ȸ�� ���� �Է��� ��ҵǾ����ϴ�.%n");
		}
	}
	
	
	//2.ȸ��������ü���
	public void menu2() {
		System.out.println();
		System.out.println("ȸ�����̵� / �̸� / ��ȭ��ȣ / �̸��� / �����");
		System.out.println("----------------------------------------------");
		List<Member> list = this.dao.memberList();
		for (Member m : list) {
			System.out.println(m.toString());
		}	
		System.out.println("----------");
		System.out.printf("�� %s��%n", list.size());		
	}
	
}
