package com.project.lsh.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.lsh.mapper.LikeMapper;

@Repository
public class LikeDao {
	@Autowired
	LikeMapper likeMapper;
	
	public String checkAlreadyLikeContent(int content_idx, int user_idx) {
		return likeMapper.checkAlreadyLikeContent(content_idx, user_idx);
	}
	
	public int insertLikeContent(int content_idx, int user_idx, String like_flag) {
		return likeMapper.insertLikeContent(content_idx, user_idx, like_flag);
	}
	
	public void updateLikeContent(int content_idx, int user_idx, String like_flag) {
		likeMapper.updateLikeContent(content_idx, user_idx, like_flag);
	}
	
	public void deleteLikeContent(int content_idx, int user_idx) {
		likeMapper.deleteLikeContent(content_idx, user_idx);
	}
	
	public String checkAlreadyLikeComment(int comment_idx, int user_idx) {
		return likeMapper.checkAlreadyLikeComment(comment_idx, user_idx);
	}
	
	public int insertLikeComment(int content_idx, int comment_idx, int user_idx, String like_flag) {
		return likeMapper.insertLikeComment(content_idx, comment_idx, user_idx, like_flag);
	}
	
	public void updateLikeComment(int comment_idx, int user_idx, String like_flag) {
		likeMapper.updateLikeComment(comment_idx, user_idx, like_flag);
	}
	
	public void deleteLikeComment(int comment_idx, int user_idx) {
		likeMapper.deleteLikeComment(comment_idx, user_idx);
	}
}
