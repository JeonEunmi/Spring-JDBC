package com.member.instructorver;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;

public class MemberService {

	// @Autowired 어노테이션 설정 추가
	// 필드 변수에 의한 의존주입 설정
	// ->필드 초기화 역할
	// ->외부(스프링)에서 실행에 필요한 자료(객체)들을 제공한다
	// ->의존주입(Dependency Injection, DI)
	// 주의) byType 방식으로 작동됨
	//인터페이스 자료형 선언을 권장한다.
	@Autowired
	private MemberDAO dao;
	
	//1.회원정보입력
	public void menu1(Scanner sc) {
		System.out.println("신규 회원 정보를 입력하는 과정입니다.");
		//회원아이디 -> 자동 증가
		//이름, 전화번호, 이메일 -> 외부 입력
		//등록일 -> 시스템의 오늘 날짜 
		System.out.print("이름>");
		String name = sc.nextLine();
		System.out.print("전화번호>");
		String phone = sc.nextLine();
		System.out.print("이메일>");
		String email = sc.nextLine();
		System.out.println();
		System.out.printf("이름:%s, 전화번호:%s, 이메일:%s%n", name, phone, email);
		System.out.print("신규 회원 정보를 입력할까요(0/1)>");
		int m = sc.nextInt();
		sc.nextLine();
		if (m == 1) {
			Member temp = new Member(null, name, phone, email, null);
			this.dao.memberAdd(temp);
			System.out.printf("신규 회원 정보가 입력되었습니다%n");
		} else {
			System.out.printf("신규 회원 정보 입력이 취소되었습니다.%n");
		}
	}
	
	
	//2.회원정보전체출력
	public void menu2() {
		System.out.println();
		System.out.println("회원아이디 / 이름 / 전화번호 / 이메일 / 등록일");
		System.out.println("----------------------------------------------");
		List<Member> list = this.dao.memberList();
		for (Member m : list) {
			System.out.println(m.toString());
		}	
		System.out.println("----------");
		System.out.printf("총 %s건%n", list.size());		
	}
	
}
