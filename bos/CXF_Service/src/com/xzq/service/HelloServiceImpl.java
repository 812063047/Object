package com.xzq.service;

public class HelloServiceImpl implements HelloService {

	@Override
	public String sayHello(String name) {
		System.err.println("����cxf�����ķ����sayHello����������");
		return "Hello" +  name;
	}

}
