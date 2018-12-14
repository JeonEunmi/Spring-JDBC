package com.member.instructorver;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Main {

	public static void main(String[] args) {
		// ȸ������ v3.0
		// 1.ȸ�������Է� 2.ȸ��������ü��� 3.����

		// ������ �����̳� �غ�
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		
		//������ �����̳ʿ��� ��ü ��û
		//-> ����) ���� ��ü �������� �ʴ´�.
		MemberService s = (MemberService) context.getBean("memberService");

		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.println();
			System.out.println("** ȸ������ v3.0 **");
			System.out.println("1.ȸ�������Է�  2.ȸ��������ü���  3.����");
			System.out.print("����>");
			int m = sc.nextInt();
			sc.nextLine();

			if (m == 0) {
				break;
			}
			switch (m) {
			case 1:
				s.menu1(sc);
				break;
			case 2:
				s.menu2();
				break;
			}
		}
		sc.close();

		// IoC(Inversion of Control) �����̳� �Ҹ� ��û
		((ClassPathXmlApplicationContext) context).close();

		System.out.println("���α׷� ����");

	}

}
