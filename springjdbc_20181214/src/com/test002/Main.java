package com.test002;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {

		// 스프링 컨테이너 준비
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");

		// 객체 요청
		MemberJDBCTemplate memberJDBCTemplate = (MemberJDBCTemplate) context.getBean("memberJDBCTemplate");

		// 특정 메소드 호출
		memberJDBCTemplate.connectionTest();
		System.out.println("----------------");
				
		// 전체 출력인 경우
		List<Member> list = memberJDBCTemplate.memberList();
		for (Member m : list) {
			System.out.println(m.toString());
		}

		System.out.println("----------------");

		// 검색 출력인 경우
		String key = "phone";
		String value = "1212";

		List<Member> list2 = memberJDBCTemplate.memberSearch(key, value);
		for (Member m : list2) {
			System.out.println(m.toString());
		}

		//입력의 경우
		int result = memberJDBCTemplate.add(new Member(null, "park", "010-2252-2222"));
		System.out.println(result + "건의 입력이 완료 되었습니다.");
		
		//삭제의 경우
		int result2 = memberJDBCTemplate.remove("M03");
		System.out.println(result2 + "개의 행이 삭제 되었습니다.");
		
		
		//IoC(Inversion of Control) 컨테이너 소멸 요청
		((ClassPathXmlApplicationContext) context).close();
	}

}
