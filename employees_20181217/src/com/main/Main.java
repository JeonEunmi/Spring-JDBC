package com.main;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.service.Service;

//메인 메뉴 운영 클래스
public class Main {

	public static void main(String[] args) {
		
		//Spring Framework 작동 순서
		/*
		1. 스프링이 사용하는 메모리 영역 준비
		   - ApplicationContext
		2. 관리하고자 하는 객체에 대한 정보 확인용 설정 파일
		   - xml
		3. 설정 파일을 근거로 객체 탐색 및 객체 생성
		4. 특정 객체가 다른 객체를 필요로 하는 경우 의존주입 실행
		5. 특정 객체에 의한 애플리케이션 진행
		*/
		
		ApplicationContext context = new ClassPathXmlApplicationContext("com/config/Beans.xml");
		
		/*
		--------------
		직원관리 by SpringJDBC
		--------------
		1.로그인
		선택(0/1)>
		*/
		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.println();
			System.out.println();
			System.out.println("--------------");
			System.out.println("직원관리 by SpringJDBC");
			System.out.println("--------------");
			System.out.println("1.로그인");
			System.out.print("선택(0/1)>");
			int m = sc.nextInt();
			sc.nextLine();

			if (m == 0) {
				break;
			}
			switch (m) {
			case 1:
				//new Service Bean 요청 
				//->scope="prototype" 속성
				((Service)context.getBean("service")).login(sc);
				break;
			}
		}
		sc.close();
		
		((ClassPathXmlApplicationContext)context).close();
		
		System.out.println("프로그램 종료");
	}

}
