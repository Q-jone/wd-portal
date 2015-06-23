package com.wonders.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.wonders.stpt.core.userManage.entity.bo.StptUser;
import com.wonders.stpt.core.userManage.service.StptUserService;

@Component("helloWorld")
public class HelloWorld {
	public String zs(String i){
		String name = "";
		try{
			StptUser u = service.findStptUserById(4545L);
			name = u.getName();
			System.out.println("in " + u.getName());
		}catch (Exception e){
			e.printStackTrace();
			name = "error";
		}
		return "HelloWolrd "+i + " " +name;
		
		
	}
	
	private static StptUserService service;

	public static StptUserService getService() {
		return service;
	}

	@Autowired(required=false)
	public void setStptUserService(@Qualifier("stptUserService")StptUserService service) {
		HelloWorld.service = service;
	}
	
}
