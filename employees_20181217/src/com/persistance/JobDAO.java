package com.persistance;

import java.util.List;

import com.domain.Job;


public interface JobDAO {

	// 출력
	public List<Job> list();

	// 입력
	public int add(Job j);

	// 삭제
	public int remove(Job j);
}
