package com.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.domain.Region;
import com.persistance.RegionDAOImpl;

public class Test {

	public static void main(String[] args) {
		
		//Spring Framework 작동 순서
		/*
		1. 스프링이 사용하는 메모리 영역 준비
		   - ApplicationContext
		2. 관리하고자 하는 객체에 대한 정보 확인용 설정 파일
		   - xml
		3. 설정 파일을 근거로 객체 탐색 및 객체 생성
		4. 특정 객체가 다른 객체를 필요로 하는 경우 의존주입 실행
		5. 특정 객체에 의한 애플리케이션 진행
		*/
		
		ApplicationContext context = new ClassPathXmlApplicationContext("com/config/Beans.xml");

		//RegionDAOImpl 클래스의 작동 테스트
		RegionDAOImpl regionDAO 
			= (RegionDAOImpl)context.getBean("regionDAOImpl");
		
		List<Region> regionList = regionDAO.list();
		for (Region r : regionList) {
			System.out.println(r.print2());
		}
		
		/*
		int result = regionDAO.add(new Region("테스트"));
		System.out.printf("%d개의 행이 삽입되었습니다.%n", result);
		*/
		
		/*
		int result = regionDAO.remove(new Region("REG03", null));
		System.out.printf("%d개의 행이 삭제되었습니다.%n", result);
		*/
		
		((ClassPathXmlApplicationContext)context).close();

	}

}
