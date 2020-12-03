package com.project.lsh.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.project.lsh.beans.CommentBean;
import com.project.lsh.beans.UserBean;
import com.project.lsh.service.CommentService;

public class CheckCommentWriterInterceptor implements HandlerInterceptor {
	private UserBean loginUserBean;
	private CommentService commentService;

	public CheckCommentWriterInterceptor(UserBean loginUserBean, CommentService commentService) {
		this.loginUserBean = loginUserBean;
		this.commentService = commentService;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String str = request.getParameter("comment_idx");
		int comment_idx = Integer.parseInt(str);
		
		CommentBean currentCommentBean = commentService.selectComment(comment_idx);
		
		if (currentCommentBean.getComment_writer_idx() != loginUserBean.getUser_idx()) {
			String contextPath = request.getContextPath();
			response.sendRedirect(contextPath + "/board/not_writer");
			
			return false;
		}
		
		return true;
	}
}
