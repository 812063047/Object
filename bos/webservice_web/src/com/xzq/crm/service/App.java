package com.xzq.crm.service;

import java.util.List;

public class App {
	public static void main(String[] args) {
		CustomerServiceImplService cc=new CustomerServiceImplService();
		ICustomerService proxy=cc.getCustomerServiceImplPort();
		List<Customer> list=proxy.findAll();
		for (Customer customer : list) {
			System.err.println(customer);
		}
	}
}
