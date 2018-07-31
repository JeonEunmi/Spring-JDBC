package com.employee.main;

import java.util.*;

import com.employee.service.SubMain;

// 메인화면 구동 클래스
public class Main {

	public static void main(String[] args) {
		
		// 메인 메뉴 구성 및 운영
		
		boolean flag = true;
		
		SubMain sub = new SubMain();
		
		Scanner sc = new Scanner(System.in);
		
			while(flag) {
				try {
			
					System.out.println("=================");
					System.out.println(" ★직원 관리  v1.0");
					System.out.println("=================");
					
					System.out.println("1. 직원 관리 | 2. 기초 정보 관리");
					System.out.print("선택(1~2, 0 exit)?  ");
					
					// 예외 처리 코드 추가 권장
					int selectNum = 0;
					selectNum = sc.nextInt();
					sc.nextLine();
			
				
					switch(selectNum) {
					case 1 :
						sub.mainMenu1(sc);
						break;
					case 2 :
						sub.mainMenu2(sc);
						break;
					case 0 :
						flag = false;
					}
				}catch(Exception e) {
					sc.nextLine();
					System.out.println("♨♨ 잘못된 메뉴 선택입니다.");
				}
				
				System.out.println();
			}
			
			
			
			sc.close();
			System.out.println("프로그램 종료");
			
		}
}

