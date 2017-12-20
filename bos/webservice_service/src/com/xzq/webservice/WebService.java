package com.xzq.webservice;

import javax.xml.ws.Endpoint;

@javax.jws.WebService
public class WebService {
	public String sayHello(String name) {
		System.out.println("服务端的sayHello方法被调用了。。。。");
		return "hello"+ name;
	}
	public static void main(String [] args) {
		String address="http://192.168.2.60:8080/hello";
		Object implementor=new WebService();
		Endpoint.publish(address, implementor);
	}
}
