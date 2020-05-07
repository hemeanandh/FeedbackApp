package com.basepackage;

import org.springframework.stereotype.Component;

@Component
public class DummyClass {
	
	public String hello() {
		
		System.out.println("hello printed");
		return "hello world";
	}

}
