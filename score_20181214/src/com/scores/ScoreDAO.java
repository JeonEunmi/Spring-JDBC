package com.scores;

import java.util.List;

public interface ScoreDAO {
	
	//���� ���� �Է� �޼ҵ�
	public int scoreAdd(Score s);

	//���� ���� ��� �� �˻� �޼ҵ�
	public List<Score> scoreList(String key, String value);

}
