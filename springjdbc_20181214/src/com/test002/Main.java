package com.test002;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {

		// ������ �����̳� �غ�
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");

		// ��ü ��û
		MemberJDBCTemplate memberJDBCTemplate = (MemberJDBCTemplate) context.getBean("memberJDBCTemplate");

		// Ư�� �޼ҵ� ȣ��
		memberJDBCTemplate.connectionTest();
		System.out.println("----------------");
				
		// ��ü ����� ���
		List<Member> list = memberJDBCTemplate.memberList();
		for (Member m : list) {
			System.out.println(m.toString());
		}

		System.out.println("----------------");

		// �˻� ����� ���
		String key = "phone";
		String value = "1212";

		List<Member> list2 = memberJDBCTemplate.memberSearch(key, value);
		for (Member m : list2) {
			System.out.println(m.toString());
		}

		//�Է��� ���
		int result = memberJDBCTemplate.add(new Member(null, "park", "010-2252-2222"));
		System.out.println(result + "���� �Է��� �Ϸ� �Ǿ����ϴ�.");
		
		//������ ���
		int result2 = memberJDBCTemplate.remove("M03");
		System.out.println(result2 + "���� ���� ���� �Ǿ����ϴ�.");
		
		
		//IoC(Inversion of Control) �����̳� �Ҹ� ��û
		((ClassPathXmlApplicationContext) context).close();
	}

}
