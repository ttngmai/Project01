package com.project.lsh.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.project.lsh.beans.BoardBean;
import com.project.lsh.beans.UserBean;
import com.project.lsh.service.TopNavService;

public class TopNavInterceptor implements HandlerInterceptor {
	private TopNavService topNavService;
	private UserBean loginUserBean;
	
	public TopNavInterceptor(TopNavService topNavService, UserBean loginUserBean) {
		this.topNavService = topNavService;
		this.loginUserBean = loginUserBean;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		List<BoardBean> topNavList = topNavService.selectTopNavList();
		
		request.setAttribute("topNavList", topNavList);
		request.setAttribute("loginUserBean", loginUserBean);
		
		return true;
	}
}
