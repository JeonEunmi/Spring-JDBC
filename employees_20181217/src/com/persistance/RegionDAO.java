package com.persistance;

import java.util.List;

import com.domain.Region;

public interface RegionDAO {

	//���
	public List<Region> list();
	
	//�Է�
	public int add(Region r);
	
	//����
	public int remove(Region r);
}
