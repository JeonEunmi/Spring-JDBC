package com.schedule01;

import java.util.*;

// 메인 메뉴 운영 클래스 선언
public class Main {

	public static void main(String[] args) {
		// 일정 관리 v1.1 (콘솔 버전)
		//
		// 1. 요구분석
		// - 일정 입력, 일정 출력 및 검색(오늘 기준, 특정 일 기준, 특정 월 기준, 특정 단어 기준, 전체)
		// - 무조건 날짜 순 정렬하기  -> 주의! (검색출력, 전체출력 모두. 같은 날짜는 순서 상관없음)

		SubMain sub = new SubMain();

		Scanner sc = new Scanner(System.in);
		
		boolean flag = true;
		int selectNum = 0;
		
		System.out.println("** 일정 관리 v1.0 **");
		
		while(flag) {
		System.out.println("======================================================");
		System.out.println("1. 일정 입력  |  2. 일정 출력 및 검색  |  3. 종료");
		System.out.println("======================================================");
		System.out.print("선택 > ");
		selectNum = sc.nextInt();
		sc.nextLine();
		
		switch(selectNum) {
		
		case 1 : 
			sub.scheduleAdd(sc);
			break;
		
		case 2 :
			sub.scheduleSearch(sc);
			break;
			
		case 3 :
			flag = false;
		}
		
		}
		
		sc.close();
		System.out.println("프로그램 종료");
	}

}
