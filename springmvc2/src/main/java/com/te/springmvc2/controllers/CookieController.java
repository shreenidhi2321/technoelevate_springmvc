package com.te.springmvc2.controllers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class CookieController {
	@GetMapping("/cookiesPage")
	public String getCookies() {
		return "cookiePage";

	}

	@GetMapping("/showCookie")
	public String name(ModelMap map, @CookieValue(name = "EmpName") String name) {

		map.addAttribute("emp", name);
		return "cookiePage";

	}

	@GetMapping("/createcookie")
	public String createCookies(HttpServletResponse response, ModelMap map) {
		Cookie cookie = new Cookie("EmpName", "Puru");
		response.addCookie(cookie);
		map.addAttribute("msg", "cookie created sucessfully");
		return "cookiePage";
	}

}
