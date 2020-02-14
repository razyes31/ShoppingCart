package com.rajesh.service;

import java.util.HashMap;
import java.util.Map;

public class LoginService {
	
private Map<String,String> users=new HashMap<>();
	
	public LoginService() {
		users.put("admin", "admin");
		users.put("rajesh","rajesh");
	}

	public boolean validate(String username, String password) {
		String strPassword = users.get(username);
		if(strPassword!=null && strPassword.equalsIgnoreCase(password)) {
			return true;
		}
		return false;

	}


}
