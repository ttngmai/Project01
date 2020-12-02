package com.project.lsh.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.lsh.beans.CommentBean;
import com.project.lsh.beans.ContentBean;
import com.project.lsh.beans.UserBean;
import com.project.lsh.dao.BoardDao;
import com.project.lsh.dao.CommentDao;
import com.project.lsh.dao.LikeDao;

@Service
public class LikeService {
	@Resource(name = "loginUserBean")
	private UserBean loginUserBean;
	@Autowired
	private BoardDao boardDao;
	@Autowired
	private CommentDao commentDao;
	@Autowired
	private LikeDao likeDao;
	
	public ContentBean insertLikeContent(int content_idx, String like_flag) {
		int user_idx = loginUserBean.getUser_idx();
		String existingLikeFlag = likeDao.checkAlreadyLikeContent(content_idx, user_idx);
		
		if (existingLikeFlag == null) {
			likeDao.insertLikeContent(content_idx, user_idx, like_flag);
			
			ContentBean tempContentBean = boardDao.selectContentLikeCnt(content_idx);
			
			tempContentBean.setLike_flag(like_flag);
			
			return tempContentBean;
		}
		
		if (existingLikeFlag.equals(like_flag)) {
			likeDao.deleteLikeContent(content_idx, user_idx);
			
			ContentBean tempContentBean = boardDao.selectContentLikeCnt(content_idx);
			
			return tempContentBean;
		}
		
		likeDao.updateLikeContent(content_idx, user_idx, like_flag);
		
		ContentBean tempContentBean = boardDao.selectContentLikeCnt(content_idx);
		
		tempContentBean.setLike_flag(like_flag);
		
		return tempContentBean;
	}
	
	public CommentBean insertLikeComment(int content_idx, int comment_idx, String like_flag) {
		int user_idx = loginUserBean.getUser_idx();
		String existingLikeFlag = likeDao.checkAlreadyLikeComment(comment_idx, user_idx);
		
		if (existingLikeFlag == null) {
			likeDao.insertLikeComment(content_idx, comment_idx, user_idx, like_flag);
			
			CommentBean tempCommentBean = commentDao.selectCommentLikeCnt(comment_idx);
			
			tempCommentBean.setLike_flag(like_flag);
			
			return tempCommentBean;
		}
		
		if (existingLikeFlag.equals(like_flag)) {
			likeDao.deleteLikeComment(comment_idx, user_idx);
			
			CommentBean tempCommentBean = commentDao.selectCommentLikeCnt(comment_idx);
			
			return tempCommentBean;
		}
		
		likeDao.updateLikeComment(comment_idx, user_idx, like_flag);
		
		CommentBean tempCommentBean = commentDao.selectCommentLikeCnt(comment_idx);
		
		tempCommentBean.setLike_flag(like_flag);
		
		return tempCommentBean;
	}
}
