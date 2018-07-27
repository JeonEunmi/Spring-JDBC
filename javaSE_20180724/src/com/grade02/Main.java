package com.grade02;

import java.util.*;

// 메인 메뉴 운영 클래스
public class Main {

	// main() 메소드만 구성
	public static void main(String[] args) {
		// 메인 메뉴 구성
		// 1. 성적정보입력 2. 성적정보출력 3.성적정보검색 4.종료
		boolean flag = true;
		Scanner sc = new Scanner(System.in);
		SubMain sub = new SubMain();
		
		System.out.println("**성적관리 v1.0**");
		
		while(flag) {
			
			System.out.println("======================================================");
			System.out.println("1. 성적정보입력 2. 성적정보출력 3.성적정보검색 4.종료");
			System.out.println("======================================================");
			System.out.print("선택 >  ");
			
			int selectNum = 0;
			selectNum = sc.nextInt();
			sc.nextLine();
			
			switch(selectNum) {
			case 1 : sub.gradeAdd(sc);
					break;
			case 2 : sub.gradeList();
					break;
			case 3 : sub.gradeSearch(sc);
					break;
			case 4 : flag = false;
			
			}


		}
		
		sc.close();
		System.out.println("프로그램 종료");
		
	}

}
