package com.persistance;
 
import java.util.List;
 
import com.domain.Job;
 
//�����ͺ��̽� ����� � Ŭ����
public interface JobDAO {
 
    // ���
    public List<Job> list();
 
    // �Է�
    public int add(Job j);
 
    // ����
    public int remove(Job j);
 
}