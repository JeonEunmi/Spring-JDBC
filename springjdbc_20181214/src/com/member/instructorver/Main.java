package com.member.instructorver;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Main {

	public static void main(String[] args) {
		// 회원관리 v3.0
		// 1.회원정보입력 2.회원정보전체출력 3.종료

		// 스프링 컨테이너 준비
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		
		//스프링 컨테이너에게 객체 요청
		//-> 주의) 직접 객체 생성하지 않는다.
		MemberService s = (MemberService) context.getBean("memberService");

		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.println();
			System.out.println("** 회원관리 v3.0 **");
			System.out.println("1.회원정보입력  2.회원정보전체출력  3.종료");
			System.out.print("선택>");
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

		// IoC(Inversion of Control) 컨테이너 소멸 요청
		((ClassPathXmlApplicationContext) context).close();

		System.out.println("프로그램 종료");

	}

}
