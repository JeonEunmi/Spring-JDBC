package com.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.domain.Region;
import com.persistance.RegionDAOImpl;

public class Test {

	public static void main(String[] args) {
		// RegionDAOImpl Ŭ������ �۵� �׽�Ʈ
		
		// ������ �����̳� �غ�
		ApplicationContext context = new ClassPathXmlApplicationContext("com/config/Beans.xml");
		
		// RegionDAOImpl Ŭ������ �۵� �׽�Ʈ
		RegionDAOImpl regionDAO = (RegionDAOImpl)context.getBean("regionDAOImpl");
		
		List<Region> regionList = regionDAO.list();
		
		for(Region r : regionList) {
			System.out.println(r.print2());
		}
		
		int result = regionDAO.remove(new Region("REG09", null));
		System.out.println(result + "���� �����Ǿ����ϴ�.");
		
		
		
		((ClassPathXmlApplicationContext)context).close();
	}

}
