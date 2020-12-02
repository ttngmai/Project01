package com.project.lsh.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.lsh.beans.UserBean;

@Controller
public class HomeController {
	@Resource(name = "loginUserBean")
	private UserBean loginUserBean;
	
	@GetMapping("/")
	public String home(HttpServletRequest request) {
		System.out.println(request.getServletContext().getRealPath("/"));
		
		return "redirect:/main";
	}
	
}
