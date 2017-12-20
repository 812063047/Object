package com.xzq.service;

public class HelloServiceImpl implements HelloService {

	@Override
	public String sayHello(String name) {
		System.err.println("基于cxf开发的服务端sayHello方法被调用");
		return "Hello" +  name;
	}

}
