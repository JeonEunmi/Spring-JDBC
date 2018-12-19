package com.persistance;

import com.domain.Login;

public interface LoginDAO {
	
	//로그인
	public Login login(String id_, String pw_);
	
	//패스워드 변경
	public int changePW(String id_, String oldPW_, String newPW_);

}
