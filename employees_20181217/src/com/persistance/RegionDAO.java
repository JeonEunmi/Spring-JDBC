package com.persistance;

import java.util.List;

import com.domain.Region;

public interface RegionDAO {

	//출력
	public List<Region> list();
	
	//입력
	public int add(Region r);
	
	//삭제
	public int remove(Region r);
}
