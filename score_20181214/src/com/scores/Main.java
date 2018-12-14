package com.scores;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//메인메뉴 액션 클래스
public class Main {

	public static void main(String[] args) {

		// 성적관리 v3.0
		// 1.성적정보입력 2.성적정보출력 3.성적정보검색 4.종료

		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");

		ScoreService service = (ScoreService) context.getBean("scoreService");

		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.println();
			System.out.println("** 성적관리 v3.0 **");
			System.out.println("1.성적정보입력  2.성적정보출력  3.성적정보검색  4.종료");
			System.out.print("선택>");
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

		System.out.println("프로그램 종료");
	}

}
