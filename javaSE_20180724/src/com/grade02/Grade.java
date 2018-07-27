package com.grade02;

// 자료형 클래스
public class Grade {

	// 필드 구성
	// ->입력, 출력에 관여하는 모든 항목 분석
	// ->번호, 이름, 과목1, 과목2, 과목3, 총점, 평균, 석차
	private String gid;
	private String name;
	private int subject1;
	private int subject2;
	private int subject3;
	private int totalSum;
	private double average;
	private int rank;
	
	// 기본생성자, 매개변수가 있는 생성자
	public Grade() {
			
	}
	
	public Grade(String gid, String name, int subject1, int subject2, int subject3) {
		this.gid = gid;
		this.name = name;
		this.subject1 = subject1;
		this.subject2 = subject2;
		this.subject3 = subject3;
		
		// 총점, 평균 연산 수행 -> 외부에서 전달 받는 것 아님
		
	}
	// getter, setter
	public String getGid() {
		return this.gid;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSubject1() {
		return this.subject1;
	}

	public void setSubject1(int subject1) {
		this.subject1 = subject1;
	}

	public int getSubject2() {
		return this.subject2;
	}

	public void setSubject2(int subject2) {
		this.subject2 = subject2;
	}

	public int getSubject3() {
		return this.subject3;
	}

	public void setSubject3(int subject3) {
		this.subject3 = subject3;
	}

	public int getRank() {
		return this.rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getTotalSum() {
		// 총점 연산 수행
		this.totalSum = this.getSubject1() + this.getSubject2() + this.getSubject3();
		
		return this.totalSum;
	}

	public double getAverage() {
		// 평균 연산 수행
		// 주의))
		// this.total이 아니라 this.getTotal()로 연결되어야 한다.
		// this.total로 작성할 경우 -> this.getTotal()이 먼저 실행된다는 보장이 없으므로 오류 발생
		// this.getTotal()로 작성하여 실행해주어야 한다.
		this.average = this.getTotalSum() / 3.0;
		
		return this.average;
	}
	
	// toString() 메소드
	// 주의))
	// this. 가아니라 this.get () 으로 작성
	// 점수가 작성되기 전에 toString()이 실행되면 오류 발생 -> 실행순서상의 이유 때문에
	
	//double 자료형 출력시 소수 이하 자릿수 지정 -> %.자릿수f   (%전체자릿수(오른쪽끝을 일정하게맞출때).자릿수f)
	public String toString() {
		String result = "";
		result = String.format("%-6s  | %-6s| %3d | %3d | %3d | %3d | %3.1f | %3d",
						this.getGid(), this.getName(), this.getSubject1(), this.getSubject2(), this.getSubject3()
						, this.getTotalSum(), this.getAverage(), this.getRank());
		return result;
	}
}
