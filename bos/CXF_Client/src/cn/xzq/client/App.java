package cn.xzq.client;

import org.omg.CORBA.CTX_RESTRICT_SCOPE;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
	public static void main(String[] args) {
		ApplicationContext context=new ClassPathXmlApplicationContext("cxf.xml");
		HelloService proxy=(HelloService)context.getBean("myClient");
		String ret=proxy.sayHello("test");
		System.err.println(ret);
	}
}
