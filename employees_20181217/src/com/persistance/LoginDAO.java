package com.persistance;

import com.domain.Login;

public interface LoginDAO {

	//출력
	public Login login(Login l);
	
	//입력
	public int add(Login l);
	
	//비밀번호변경
	public int pwupdate(Login l);
}
