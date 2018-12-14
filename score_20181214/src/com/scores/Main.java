package com.scores;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//���θ޴� �׼� Ŭ����
public class Main {

	public static void main(String[] args) {

		// �������� v3.0
		// 1.���������Է� 2.����������� 3.���������˻� 4.����

		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");

		ScoreService service = (ScoreService) context.getBean("scoreService");

		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.println();
			System.out.println("** �������� v3.0 **");
			System.out.println("1.���������Է�  2.�����������  3.���������˻�  4.����");
			System.out.print("����>");
			int m = sc.nextInt();
			sc.nextLine();

			if (m == 0 || m == 4) {
				break;
			}
			
			switch (m) {
			case 1:
				service.menu1(sc);
				break;
			case 2:
				service.menu2();
				break;
			case 3:
				service.menu3(sc);
				break;
			}
		}
		sc.close();

		((ClassPathXmlApplicationContext) context).close();

		System.out.println("���α׷� ����");
	}

}
