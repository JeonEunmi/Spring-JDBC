package com.scores;

import java.util.List;

public interface ScoreDAO {
	
	//성적 정보 입력 메소드
	public int scoreAdd(Score s);

	//성적 정보 출력 및 검색 메소드
	public List<Score> scoreList(String key, String value);

}
