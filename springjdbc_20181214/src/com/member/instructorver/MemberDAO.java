package com.member.instructorver;

import java.util.List;

public interface MemberDAO {
	
	//ȸ�� ���� �Է� �޼ҵ�
	public int memberAdd(Member m);
	
	//ȸ�� ���� ��� �޼ҵ�
	public List<Member> memberList();
	
	

}
