package com.grade02;

import java.util.*;

// 메인 메뉴별 액션 클래스
public class SubMain {

	// 필드 구성 -> 자료 저장소 운영 클래스의 객체
	private GradeList g = new GradeList();
	
	// 1. 성적정보입력 메뉴 메소드
	public void gradeAdd (Scanner sc) {

		System.out.println("------------------------------------------------------");
		System.out.println("             성   적   정   보   입   력              ");
		System.out.println("------------------------------------------------------");
		System.out.print("이름 : ");
		String name = sc.nextLine();
		System.out.print("과목1 점수 : ");
		int sub1 = sc.nextInt();
		sc.nextLine();
		System.out.print("과목2 점수 : ");
		int sub2 = sc.nextInt();
		sc.nextLine();
		System.out.print("과목3 점수 : ");
		int sub3 = sc.nextInt();
		sc.nextLine();		
		String gid = g.autoGid();
		
		Grade tmp = new Grade(gid, name, sub1, sub2, sub3);
		this.g.gradeAdd(tmp);
		System.out.println("성적 정보 입력이 성공했습니다.");
		
		
	}
	
	// 2. 성적정보출력 메뉴 메소드
	public void gradeList () {
		System.out.println("------------------------------------------------------");
		System.out.println("번호     이름    과목1 과목2 과목3  총점  평균   석차");
		System.out.println("------------------------------------------------------");
		
		Grade[] gradeList = this.g.gradeList();
		
		// 정렬 기능 추가 -> null값 없는 배열 준비
		Arrays.sort(gradeList, new Comparator<Grade>() {

			@Override
			public int compare(Grade o1, Grade o2) {
				return o1.getGid().compareTo(o2.getGid());
			}
			
		});
		
		int count = gradeList.length;
		for(Grade g : gradeList) {
			//toString() 메소드 생략 가능
				System.out.println(g.toString());				
		}
		
		System.out.println("------------------------------------------------------");
		System.out.println("총 : " + count + "건");
		
	}
	
	// 3. 성적정보검색 메뉴 메소드
	public void gradeSearch (Scanner sc) {
		boolean flag = true;
		
		while(flag) {
			System.out.println("======================================================");
			System.out.println("1. 번호기준  2. 이름기준  3. 종료");
			System.out.println("======================================================");
			System.out.print("선택 > ");
			
			
			int selectNo = sc.nextInt();
			sc.nextLine();
			
			switch(selectNo) {
			case 1 : this.gradeSearchGid(sc);
			break;
			case 2 : this.gradeSearchName(sc);
			break;
			case 3 : flag = false;
			}
			
		}
		// 서브 메뉴 운영
		// 1. 번호기준  2. 이름기준  3. 종료
		
	}
	
	// 1. 번호기준 메뉴 메소드 -> private method
	private void gradeSearchGid(Scanner sc) {

		System.out.print("검색할 번호를 입력해주세요. > ");
		String value = sc.nextLine();
		int count = 0;
		
		Grade[] tmp = g.gradeSearch("gid", value);
		
		System.out.println("------------------------------------------------------");
		System.out.println("번호     이름    과목1 과목2 과목3  총점  평균   석차");
		System.out.println("------------------------------------------------------");
		for(Grade g : tmp) {
			if(g != null) {
				System.out.println(g.toString());
				++count;
			} 
		}
		if(count == 0)  {
			System.out.println("검색결과가 없습니다.");
		}
		System.out.println("------------------------------------------------------");
		System.out.println("총 " + count + "건");
		System.out.println();
	}
	
	// 2. 이름기준 메뉴 메소드 -> private method
	private void gradeSearchName(Scanner sc) {

		System.out.print("검색할 이름을 입력해주세요. > ");
		String value = sc.nextLine();
		int count = 0;
		
		Grade[] tmp = g.gradeSearch("name", value);
		
		System.out.println("------------------------------------------------------");
		System.out.println("번호     이름    과목1 과목2 과목3  총점  평균   석차");
		System.out.println("------------------------------------------------------");
		for(Grade g : tmp) {
			if(g != null) {
				System.out.println(g.toString());
				++count;
			}
		} if(count == 0 ) {
			System.out.println("검색결과가 없습니다.");
		}
		System.out.println("------------------------------------------------------");
		System.out.println("총 " + count + "건");
		System.out.println();
		
	}
}
	

