package com.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.domain.Region;
import com.persistance.RegionDAOImpl;

public class Test {

	public static void main(String[] args) {
		
		//Spring Framework �۵� ����
		/*
		1. �������� ����ϴ� �޸� ���� �غ�
		   - ApplicationContext
		2. �����ϰ��� �ϴ� ��ü�� ���� ���� Ȯ�ο� ���� ����
		   - xml
		3. ���� ������ �ٰŷ� ��ü Ž�� �� ��ü ����
		4. Ư�� ��ü�� �ٸ� ��ü�� �ʿ�� �ϴ� ��� �������� ����
		5. Ư�� ��ü�� ���� ���ø����̼� ����
		*/
		
		ApplicationContext context = new ClassPathXmlApplicationContext("com/config/Beans.xml");

		//RegionDAOImpl Ŭ������ �۵� �׽�Ʈ
		RegionDAOImpl regionDAO 
			= (RegionDAOImpl)context.getBean("regionDAOImpl");
		
		List<Region> regionList = regionDAO.list();
		for (Region r : regionList) {
			System.out.println(r.print2());
		}
		
		/*
		int result = regionDAO.add(new Region("�׽�Ʈ"));
		System.out.printf("%d���� ���� ���ԵǾ����ϴ�.%n", result);
		*/
		
		/*
		int result = regionDAO.remove(new Region("REG03", null));
		System.out.printf("%d���� ���� �����Ǿ����ϴ�.%n", result);
		*/
		
		((ClassPathXmlApplicationContext)context).close();

	}

}
