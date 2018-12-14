package com.test001;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
	      ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
	      MemberJDBCTemplate memberJDBCTemplate = 
	         (MemberJDBCTemplate)context.getBean("memberJDBCTemplate");
	     
	      //memberJDBCTemplate.connectionTest();
	      
	      List<Member> list = memberJDBCTemplate.memberList();
	      for(Member m : list) {
	    	  System.out.println(m.toString());
	      }
	      
	      System.out.println("----------------");
	      
	      String key = "phone";
	      String value = "1212";
	      
	      List<Member> list2 = memberJDBCTemplate.memberSearch(key, value);
	      for(Member m : list2) {
	    	  System.out.println(m.toString());
	      }
	      
	      ((ClassPathXmlApplicationContext)context).close();
	}

}
