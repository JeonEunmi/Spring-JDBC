package com.persistance;
 
import java.util.*;
 
import com.domain.*;
 
public interface DepartmentDAO {
 
    // ���
    public List<Department> list() ;
 
    // �Է�
    public int add(Department d) ;
 
    // ����
    public int remove(Department d);
    
}