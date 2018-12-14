package com.scores;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;

//메인 메뉴별 액션 클래스
public class ScoreService {

	private ScoreDAO scoreDAO;

	public void setScoreDAO(ScoreDAO scoreDAO) {
		this.scoreDAO = scoreDAO;
	}

	// 성적 정보 입력
	public void menu1(Scanner sc) {
		System.out.println("신규 성적 정보를 입력하는 과정입니다.");
		System.out.print("이름>");
		String name_ = sc.nextLine();
		System.out.print("과목1>");
		int subject1 = sc.nextInt();
		sc.nextLine();
		System.out.print("과목2>");
		int subject2 = sc.nextInt();
		sc.nextLine();
		System.out.print("과목3>");
		int subject3 = sc.nextInt();
		sc.nextLine();
		Score temp = new Score(null, name_, subject1, subject2, subject3, 0, 0.0, 0);
		int result = this.scoreDAO.scoreAdd(temp);
		System.out.printf("%s 행 이(가) 삽입되었습니다.%n", result);
	}

	// 성적 정보 출력
	public void menu2() {
		List<Score> list = this.scoreDAO.scoreList("all", "");
		this.printScores(list);
	}

	// 성적 정보 검색
	public void menu3(Scanner sc) {
		// 서브메뉴 운영
		// 1.번호기준 2.이름기준 3.종료
		while (true) {
			System.out.println();
			System.out.println("**성적관리 v2.0 ** > 3.성적정보검색");
			System.out.println("1.번호기준  2.이름기준  3.종료");
			System.out.print("선택>");
			int m = sc.nextInt();
			sc.nextLine();

			if (m == 0 || m == 3) {
				break;
			}
			switch (m) {
			case 1:
				this.menu3_sub1(sc);
				break;
			case 2:
				this.menu3_sub2(sc);
				break;
			}
		}
	}

	// 성적 정보 검색 > 1.번호
	private void menu3_sub1(Scanner sc) {
		System.out.print("번호>");
		String sid_ = sc.nextLine();
		List<Score> list = this.scoreDAO.scoreList("sid_", sid_);
		if (list.size() > 0) {
			this.printScores(list);
		} else {
			System.out.println("검색 결과가 없습니다.");
		}
	}

	// 성적 정보 검색 > 2.이름
	private void menu3_sub2(Scanner sc) {
		System.out.print("이름>");
		String name_ = sc.nextLine();
		List<Score> list = this.scoreDAO.scoreList("name_", name_);
		if (list.size() > 0) {
			this.printScores(list);
		} else {
			System.out.println("검색 결과가 없습니다.");
		}
	}

	// 성적 출력 전용 private 메소드
	private void printScores(List<Score> list) {
		System.out.println("번호 / 이름 / 과목1 / 과목2 / 과목3 / 총점 / 평균 / 석차");
		System.out.println("--------------------------------------------------------");
		for (Score s : list) {
			System.out.println(s.toString());
		}
		System.out.println("----------");
		System.out.printf("총 %s건%n", list.size());
	}

}
