package com.project.lsh.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.project.lsh.beans.UserBean;

public class CheckAuthStatusInterceptor implements HandlerInterceptor {
	private UserBean loginUserBean;
	
	public CheckAuthStatusInterceptor(UserBean loginUserBean) {
		this.loginUserBean = loginUserBean;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (loginUserBean.getUser_auth_status().equals("N")) {
			String contextPath = request.getContextPath();
			response.sendRedirect(contextPath + "/user/not_auth_user");
			
			return false;
		}
		
		return true;
	}
}
