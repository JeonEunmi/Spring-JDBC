package com.main;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.service.Service;

//���� �޴� � Ŭ����
public class Main {

	public static void main(String[] args) {
		
		//Spring Framework �۵� ����
		/*
		1. �������� ����ϴ� �޸� ���� �غ�
		   - ApplicationContext
		2. �����ϰ��� �ϴ� ��ü�� ���� ���� Ȯ�ο� ���� ����
		   - xml
		3. ���� ������ �ٰŷ� ��ü Ž�� �� ��ü ����
		4. Ư�� ��ü�� �ٸ� ��ü�� �ʿ�� �ϴ� ��� �������� ����
		5. Ư�� ��ü�� ���� ���ø����̼� ����
		*/
		
		ApplicationContext context = new ClassPathXmlApplicationContext("com/config/Beans.xml");
		
		/*
		--------------
		�������� by SpringJDBC
		--------------
		1.�α���
		����(0/1)>
		*/
		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.println();
			System.out.println();
			System.out.println("--------------");
			System.out.println("�������� by SpringJDBC");
			System.out.println("--------------");
			System.out.println("1.�α���");
			System.out.print("����(0/1)>");
			int m = sc.nextInt();
			sc.nextLine();

			if (m == 0) {
				break;
			}
			switch (m) {
			case 1:
				//new Service Bean ��û 
				//->scope="prototype" �Ӽ�
				((Service)context.getBean("service")).login(sc);
				break;
			}
		}
		sc.close();
		
		((ClassPathXmlApplicationContext)context).close();
		
		System.out.println("���α׷� ����");
	}

}
