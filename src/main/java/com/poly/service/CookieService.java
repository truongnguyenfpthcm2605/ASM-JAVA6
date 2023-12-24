package com.poly.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class CookieService {
	@Autowired
	HttpServletResponse response;
	
	@Autowired
	HttpServletRequest request;
	
	public void add(String name,String value, int days) {
		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(days*60*60);
		cookie.setPath("/");
		response.addCookie(cookie);
	}
	
	public Cookie get(String name) {
		Cookie[] cookie = request.getCookies();
		if(cookie!=null) {
			for (Cookie cookie2 : cookie) {
				if(cookie2.getName().equalsIgnoreCase(name));
				return cookie2;
			}
		}
		return null;
	}
	
	public void remove(String name) {
		Cookie[] cookie = request.getCookies();
		if(cookie!=null) {
			for (Cookie cookie2 : cookie) {
				if(cookie2.getName().equalsIgnoreCase(name));
				cookie2.setMaxAge(0);
				cookie2.setPath("/");
				response.addCookie(cookie2);
			}
		}
	}
	
	public String getValue(String name) {
		Cookie[] cookie = request.getCookies();
		if(cookie!=null) {
			for (Cookie cookie2 : cookie) {
				if(cookie2.getName().equalsIgnoreCase(name));
				return cookie2.getValue();
			}
		}
		return null;
	}
}
