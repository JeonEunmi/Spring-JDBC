package com.schedule01;

import java.time.LocalDate;
import java.util.*;

// 저장소 + 메인 메뉴별 액션 클래스 선언
public class SubMain {

	private ScheduleList s = new ScheduleList();

	// public method
	// 1. 일정 입력 메뉴 메소드
	public void scheduleAdd(Scanner sc) {
		// 콘솔 액션 필요
		System.out.println("");
		System.out.print("날짜(YYYY-MM-DD)? ");
		String scheduleDate = sc.nextLine();
		System.out.print("내용(200자 이내)? ");
		String scheduleContent = sc.nextLine();

		String sid = s.autoGid();

		Schedule tmp = new Schedule(sid, scheduleDate, scheduleContent);
		int result = this.s.scheduleAdd(tmp);
		
		System.out.println("일정이 입력되었습니다.");
		
		if(result == 1) {
			System.out.println();
			System.out.println("*****************  경    고  *****************");
			System.out.println("배열 저장소 초과. 자동으로 10개 증가시킵니다.");
			System.out.println("**********************************************");
			System.out.println();
		}
		
	}

	// public method
	// 2. 일정 출력 및 검색 메뉴 메소드
	public void scheduleSearch(Scanner sc) {

		while (true) {

			System.out.println();
			System.out.println("======================================================");
			System.out.println("1. 오늘 일정  |  2. 특정 일 일정  |  3. 특정 월 일정 ");
			System.out.println("4. 특정 단어 포함 일정  |   5. 전체 일정   |  6. 종료");
			System.out.println("======================================================");
			System.out.print("선택> ");

			int selectNo = 0;

			selectNo = sc.nextInt();
			sc.nextLine();

			if (selectNo == 6)
				break;

			switch (selectNo) {

			case 1:
				this.todaySchedule();
				break;
			case 2:
				this.daySchedule(sc);
				break;
			case 3:
				this.monthSchedule(sc);
				break;
			case 4:
				this.wordSearch(sc);
				break;
			case 5:
				this.allSchedule();
				break;
			}
		}

	}

	// 서브 메뉴 구성
	// -> 1. 오늘 일정 2. 특정 일 일정 3. 특정 월 일정 4. 특정 단어 포함 일정 5. 전체 일정 6. 종료

	// private method
	// 1. 오늘 일정 메뉴 메소드
	private void todaySchedule() {

		String value = LocalDate.now().toString();

		this.print(this.s.scheduleSearch("today", value));
	}

	// private method
	// 2. 특정 일 일정 메소드
	private void daySchedule(Scanner sc) {

		System.out.print("특정일 (YYYY-MM-DD)? ");
		String value = sc.nextLine();

		// 검색 전용 메소드 호출
		// null 값 없는 사본 배열 반환
		// ->출력 전용 메소드 호출
		this.print(this.s.scheduleSearch("day", value));

	}


	// private method
	// 3. 특정 월 일정 메소드
	private void monthSchedule(Scanner sc) {
		System.out.print("특정월 (YYYY-MM)? ");
		String value = sc.nextLine();

		// 검색 전용 메소드 호출
		// null 값 없는 사본 배열 반환
		// ->출력 전용 메소드 호출
		this.print(this.s.scheduleSearch("month", value));
	}

	// private method
	// 4. 특정 단어 일정 메소드
	private void wordSearch(Scanner sc) {
		System.out.print("찾고자하는 검색 단어를 입력하세요. >  ");
		String value = sc.nextLine();

		this.print(this.s.scheduleSearch("content", value));
	}

	// private method
	// 5. 전체 일정 메소드
	private void allSchedule() {
		// 출력시 일정 정보가 날짜순으로 정렬된 상태로 출력되도록 한다.
		/*
		 * this.idx가 null값이 들어있을 경우 정확하지 않을 수 있어서 count로 갯수를 다시 세어주는 것이 좋다. int count =
		 * 0; for (Schedule s : schedule){ if( s != null) ++count; }
		 */


		// 출력 전용 메소드 호출 -> print()
		this.print(this.s.allSchedule());
	}

	// private method
	// -> 정렬 + 출력 액션 메소드
	// -> null 값이 없는 배열을 외부에서 전달 받는다.
	private void print(Schedule[] allSchedule) {

		System.out.println("======================================================");
		System.out.println("번호		날짜	    내용");
		System.out.println("======================================================");

		// 정렬 액션 추가
		// Arrays.sort(allSchedule);

		// 정렬 액션 -> null 값 없는 배열인 경우만 가능
		Schedule[] schedule = this.s.allSchedule();

		Arrays.sort(schedule, new Comparator<Schedule>() {

			@Override
			public int compare(Schedule o1, Schedule o2) {
				return o1.getScheduleDate().compareTo(o2.getScheduleDate());
			}

		});

		// 출력 액션
		int count = schedule.length;
		for (Schedule s : schedule) {
			System.out.println(s.toString());
		}
		System.out.println("------------------------------------------------------");
		System.out.println("총 " + count + "건");

	}

}
