package com.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.domain.Region;
import com.persistance.RegionDAOImpl;

public class Test {

	public static void main(String[] args) {
		// RegionDAOImpl 클래스의 작동 테스트
		
		// 스프링 컨테이너 준비
		ApplicationContext context = new ClassPathXmlApplicationContext("com/config/Beans.xml");
		
		// RegionDAOImpl 클래스의 작동 테스트
		RegionDAOImpl regionDAO = (RegionDAOImpl)context.getBean("regionDAOImpl");
		
		List<Region> regionList = regionDAO.list();
		
		for(Region r : regionList) {
			System.out.println(r.print2());
		}
		
		int result = regionDAO.remove(new Region("REG09", null));
		System.out.println(result + "건이 삭제되었습니다.");
		
		
		
		((ClassPathXmlApplicationContext)context).close();
	}

}
