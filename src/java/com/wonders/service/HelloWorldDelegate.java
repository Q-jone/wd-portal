package com.wonders.service;


@javax.jws.WebService(targetNamespace = "http://zhoushun.com/", serviceName = "HelloWorldService", portName = "zhoushun_cocc")
public class HelloWorldDelegate {

	HelloWorld helloWorld = new HelloWorld();

	public String zs(String i) {
		return helloWorld.zs(i);
	}

}