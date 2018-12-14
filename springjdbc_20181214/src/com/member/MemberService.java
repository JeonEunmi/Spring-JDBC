package com.member;

import java.util.List;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class MemberService {

	// 스프링 컨테이너 준비
	ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");

	// 객체 요청
	MemberJDBCTemplate memberJDBCTemplate = (MemberJDBCTemplate) context.getBean("memberJDBCTemplate");

	// 1.회원정보입력
	public void menu1(Scanner sc) {
		System.out.println("신규 회원 정보를 입력하는 과정입니다.");
		// 회원아이디 -> 자동 증가
		// 이름, 전화번호, 이메일 -> 외부 입력
		// 등록일 -> 시스템의 오늘 날짜
		System.out.print("이름>");
		String name = sc.nextLine();
		System.out.print("전화번호>");
		String phone = sc.nextLine();
		System.out.print("이메일>");
		String email = sc.nextLine();
		Member temp = new Member(null, name, phone, email, null);

		System.out.printf("입력하신 내용은 %n 이름 : %s , 전화번호 : %s , 이메일 : %s 입니다. %n", name, phone, email);
		System.out.print("신규 회원 정보를 등록할까요? (0/1) > ");
		int select = sc.nextInt();
		sc.nextLine();

		if (select == 1) {
			int result = this.memberJDBCTemplate.add(temp);
			System.out.printf("%s 행 이(가) 삽입되었습니다.%n", result);
		} else if (select == 0) {
			System.out.println("회원등록이 취소되었습니다.");
		} else {
			System.out.println("잘못 누르셨습니다. 다시 입력해주세요.");
		}
	}

	// 2.회원정보삭제
	public void menu2(Scanner sc) {
		System.out.println();
		System.out.println("회원아이디 / 이름 / 전화번호 / 이메일 / 등록일");
		System.out.println("-------------------------------------------------------");
		List<Member> list = this.memberJDBCTemplate.memberList();
		for (Member m : list) {
			System.out.println(m.toString());
		}
		System.out.println("-------------------------------------------------------");
		System.out.printf("총 %s건%n", list.size());
		System.out.println();
		System.out.print("삭제할 회원 아이디를 입력하세요.>");
		String mid = sc.nextLine();
		
		System.out.printf("삭제하실 회원 아이디는 %s 입니다. %n", mid);
		System.out.print("회원 정보를 삭제할까요? (0/1) >");
		int select = sc.nextInt();
		sc.nextLine();
		
		if (select == 1) {
			int result = this.memberJDBCTemplate.remove(mid);
			System.out.printf("%s 행 이(가) 삭제되었습니다.%n", result);
		} else if (select == 0) {
			System.out.println("회원등록이 취소되었습니다.");
		} else {
			System.out.println("잘못 누르셨습니다. 다시 입력해주세요.");
		}
	}
	
	// 3.회원정보전체출력
	public void menu3() {
		System.out.println();
		System.out.println("회원아이디 / 이름 / 전화번호 / 이메일 / 등록일");
		System.out.println("-------------------------------------------------------");
		List<Member> list = this.memberJDBCTemplate.memberList();
		for (Member m : list) {
			System.out.println(m.toString());
		}
		System.out.println("-------------------------------------------------------");
		System.out.printf("총 %s건%n", list.size());
	}
	
	// 4.회원정보검색
	public void menu4(Scanner sc) {
		System.out.println("");
		System.out.println("1. 회원아이디  2.이름  3.전화번호  4.이메일  5.등록일");
		System.out.print("검색 기준을 선택하세요.> ");
		int select = sc.nextInt();
		sc.nextLine();
		
		System.out.print("검색 단어를 입력하세요.> ");
		
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
		
		System.out.println("회원아이디 / 이름 / 전화번호 / 이메일 / 등록일");
		System.out.println("-------------------------------------------------------");
		List<Member> list = this.memberJDBCTemplate.memberSearch(key, value);
		for (Member m : list) {
			System.out.println(m.toString());
		}
		System.out.println("-------------------------------------------------------");
		System.out.printf("총 %s건%n", list.size());
	}

}
