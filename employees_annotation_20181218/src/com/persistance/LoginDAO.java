package com.persistance;

import com.domain.Login;

public interface LoginDAO {
	
	//�α���
	public Login login(String id_, String pw_);
	
	//�н����� ����
	public int changePW(String id_, String oldPW_, String newPW_);

}
