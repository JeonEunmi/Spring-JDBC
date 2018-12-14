package com.scores;

//자료형 클래스 
public class Score {
	
	//필드 구성
	//번호, 이름, 과목1, 과목2, 과목3, 총점, 평균, 석차
	private String sid_, name_;
	private int subject1, subject2, subject3, total, rank_;
	private double avg_;
	
	public Score(String name_, int subject1, int subject2, int subject3) {
		this.name_ = name_;
		this.subject1 = subject1;
		this.subject2 = subject2;
		this.subject3 = subject3;
	}

	public Score(String sid_, String name_, int subject1, int subject2, int subject3
				, int total, double avg_, int rank_) {
		this.sid_ = sid_;
		this.name_ = name_;
		this.subject1 = subject1;
		this.subject2 = subject2;
		this.subject3 = subject3;
		this.total = total;
		this.avg_ = avg_;
		this.rank_ = rank_;
	}

	public String getSid_() {
		return sid_;
	}

	public String getName_() {
		return name_;
	}

	public int getSubject1() {
		return subject1;
	}

	public int getSubject2() {
		return subject2;
	}

	public int getSubject3() {
		return subject3;
	}

	public int getTotal() {
		return total;
	}

	public int getRank_() {
		return rank_;
	}

	public double getAvg_() {
		return avg_;
	}

	@Override
	public String toString() {
		return String.format("%s / %s / %s / %s / %s / %s / %.1f / %s"
				,  getSid_(), getName_() , getSubject1()
				,  getSubject2() , getSubject3(),  getTotal()
				 , getAvg_(),  getRank_());
	}
	
}
