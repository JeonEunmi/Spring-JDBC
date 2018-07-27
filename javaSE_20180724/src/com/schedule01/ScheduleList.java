package com.schedule01;

import java.util.Arrays;

// 저장소 운영
public class ScheduleList {

	// 필드 선언 -> 배열 저장소 운영
	private Schedule[] scheduleArray = new Schedule[10];

	private int idx;

	// 생성자 -> 필드 초기화
	public ScheduleList() {
		scheduleArray[this.idx] = new Schedule("S001", "2018-01-01", "신정");
		++this.idx;
		scheduleArray[this.idx] = new Schedule("S002", "2018-12-25", "크리스마스");
		++this.idx;
		scheduleArray[this.idx] = new Schedule("S003", "2018-06-06", "현충일");
		++this.idx;
		scheduleArray[this.idx] = new Schedule("S004", "2018-08-15", "광복절");
		++this.idx;
		scheduleArray[this.idx] = new Schedule("S005", "2018-03-01", "삼일절");
		++this.idx;
		scheduleArray[this.idx] = new Schedule("S006", "2018-10-03", "개천절");
		++this.idx;
		scheduleArray[this.idx] = new Schedule("S007", "2018-10-09", "한글날");
		++this.idx;
	}

	// 번호 자동 증가 메소드
	public String autoGid() {

		String result = "";
		result = String.format("S%03d", (this.idx + 1));
		return result;

	}

	// 스케쥴 입력
	public int scheduleAdd(Schedule schedule) {
		int a = 0;
		
		scheduleArray[this.idx] = schedule;
		++this.idx;

		if (this.scheduleArray.length == this.idx) {
			this.extendArray();
			a = 1;
		}
		
		return a;
	}

	// 출력 메소드
	public Schedule[] allSchedule() {

		Schedule[] sch = new Schedule[this.idx];
		int i = 0;
		for (Schedule s : this.scheduleArray) {
			if (s != null) {
				sch[i] = s;
				++i;
			}
		}

		return sch;

	}

	// 배열 저장소
	public void extendArray() {

		Schedule[] schedule = Arrays.copyOf(scheduleArray, scheduleArray.length + 10);

		this.scheduleArray = schedule;

	}

	// private method
	// -> 검색 전용 메소드
	// -> 검색 진행은 매개 변수가 String key, String value로 고정
	public Schedule[] scheduleSearch(String key, String value) {

		// key -> 검색 기준, 예를 들어, "특정일"
		// value -> 검색 단어. 예를 들어 "2018-07-19"
		// 검색 결과에 null 은 없음

		// 검색 결과 저장용 임시 배열 준비
		Schedule[] scheduleList = new Schedule[this.idx];

		int a = 0;

		// 배열 저장소에 있는 일정 정보를 검색하기 위한 반복문
		for (Schedule s : this.scheduleArray) {
			if (s != null) {
				// 검색 기준(key)과 검색 단어(value)에 맞는 일정
				// 임시 배열에 저장
				String temp = null;
				if (key.equals("today") || key.equals("day") || key.equals("month")) {
					temp = s.getScheduleDate();
				}

				if (key.equals("content")) {
					temp = s.getScheduleContent();
				}

				if (temp.contains(value)) {
					scheduleList[a] = s;
					++a;
				}
			}
		}

		return Arrays.copyOfRange(scheduleList, 0, a);
	}

}
