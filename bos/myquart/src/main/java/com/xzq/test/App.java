package com.xzq.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext tx= new ClassPathXmlApplicationContext("applicationContext.xml");
	}
}
