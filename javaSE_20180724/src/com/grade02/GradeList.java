package com.grade02;

import java.util.Arrays;

// 자료 저장소 운영 클래스
public class GradeList {

	// (배열) 자료 저장소 생성 -> field
	private Grade[] gradeList = new Grade[10];

	// 인덱스 관리용 변수
	private int idx;

	// 생성자 -> 초기화
	public GradeList() {
		this.gradeList[this.idx] = new Grade("G002", "PARK", 100, 56, 84);
		++this.idx;
		this.gradeList[this.idx] = new Grade("G007", "MIN", 82, 96, 69);
		++this.idx;
		this.gradeList[this.idx] = new Grade("G003", "CHOI", 75, 71, 83);
		++this.idx;
		this.gradeList[this.idx] = new Grade("G004", "JEON", 82, 35, 76);
		++this.idx;
		this.gradeList[this.idx] = new Grade("G001", "HONG", 86, 91, 43);
		++this.idx;
		this.gradeList[this.idx] = new Grade("G008", "LEE", 23, 34, 54);
		++this.idx;
		this.gradeList[this.idx] = new Grade("G005", "KIM", 87, 57, 69);
		++this.idx;
		this.gradeList[this.idx] = new Grade("G006", "YOUN", 42, 67, 62);
		++this.idx;
		
		this.gradeRank();
	}
	
	// 번호 자동 증가 메소드
		public String autoGid() {
			
			String result = "";
			result = String.format("G%03d", (this.idx+1));
			return result;
			
		}

	// 성적 입력 액션 메소드
	public void gradeAdd(Grade gradeList) {
		
		this.gradeList[this.idx] = gradeList;
		++this.idx;

		//주의) 성적입력 후 석차 계산 재실행 필요
		this.gradeRank();
	}

	// 성적 출력 액션 메소드
	public Grade[] gradeList() {

		Grade[] result = new Grade[this.idx];

		// 자료 저장소에서 null 값을 제외한 자료만 가지고 사본 배열 생성 및 반환
		int i = 0;

		for (Grade g : gradeList) {
			if (g != null) {
				result[i] = g;
				++i;
			}

		}
		return result;
	}

	// 성적 검색 액션 메소드 -> String key, String value
	public Grade[] gradeSearch(String key, String value) {
	
		// 1. 외부에서 전달받은 key, value를 가지고 검색 진행
		// 2. key 변수의 값에 따라서 다양한 기준으로 검색
		// 3. value 변수는 검색하고자하는 값이다.
		// 4. 검색 결과는 사본 배열 생성 후 반환. null 값 제외된 배열.
		
		Grade[] tmp = new Grade[this.idx];
		int a = 0;
		for (Grade g : this.gradeList) {
			if(g != null) {
				String temp = null;
				if(key.contains("gid")) {
					temp = g.getGid();
				}
				if(key.contains("name")) {
					temp = g.getName();
				}
				if(temp.contains(value)) {
					tmp[a] = g;
					++a;
				}
			}
		}
		
		return Arrays.copyOfRange(tmp, 0, a);

	}

	// 석차 계산 메소드 -> private method
	private void gradeRank() {
		
		// 1. 석차 초기값 1로 설정
		// 2. 나의 점수와 상대방 점수 비교 액션 -> 중첩된 for문
		// 3. 상대방 점수가 높은 경우 나의 등수를 +1 연산
		// 4. 계산된 석차 정보를 setter를 이용해서 저장
		//주의) null 값 비교 제외
		
		for(int i = 0; i < this.gradeList.length; ++i) {
			Grade g = this.gradeList[i];
			if(g != null) {
				g.setRank(1);				
			}
		}
		
		for(int i = 0; i < this.gradeList.length; ++i) {
				Grade g = this.gradeList[i];
				if(g != null) {
					for(int j = 0; j < this.gradeList.length; ++j) {
						Grade g2 = this.gradeList[j];
						if(g2 != null) {
							if(g.getTotalSum() < g2.getTotalSum()) {
								g.setRank(g.getRank() + 1);
							
							}
						}
					}		
				}
		}
	}
}
