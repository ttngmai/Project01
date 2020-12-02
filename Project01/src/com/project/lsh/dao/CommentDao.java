package com.project.lsh.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.lsh.beans.CommentBean;
import com.project.lsh.mapper.CommentMapper;

@Repository
public class CommentDao {
	@Autowired
	private CommentMapper commentMapper;
	
	public int insertComment(CommentBean commentBean) {
		return commentMapper.insertComment(commentBean);
	}
	
	public void updateCommentCnt(int content_idx) {
		commentMapper.updateCommentCnt(content_idx);
	}
	
	public void updateReplyCnt(int content_idx, int comment_parent_idx) {
		commentMapper.updateReplyCnt(content_idx, comment_parent_idx);
	}
	
	public List<CommentBean> selectCommentList(int content_idx, int comment_parent_idx, int user_idx, RowBounds rowBounds) {
		return commentMapper.selectCommentList(content_idx, comment_parent_idx, user_idx, rowBounds);
	}
	
	public String selectCommentText(int comment_idx) {
		return commentMapper.selectCommentText(comment_idx);
	}
	
	public CommentBean selectCommentLikeCnt(int comment_idx) {
		return commentMapper.selectCommentLikeCnt(comment_idx);
	}
	
	public int selectCommentCnt(int content_idx, int comment_parent_idx) {
		return commentMapper.selectCommentCnt(content_idx, comment_parent_idx);
	}
	
	public void updateComment(CommentBean commentBean) {
		commentMapper.updateComment(commentBean);
	}
	
	public void updateCommentDeleteStatus(int comment_idx) {
		commentMapper.updateCommentDeleteStatus(comment_idx);
	}
}
