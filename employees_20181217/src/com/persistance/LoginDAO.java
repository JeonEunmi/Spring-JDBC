package com.persistance;

import com.domain.Login;

public interface LoginDAO {

	//���
	public Login login(Login l);
	
	//�Է�
	public int add(Login l);
	
	//��й�ȣ����
	public int pwupdate(Login l);
}
