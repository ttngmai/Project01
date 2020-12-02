package com.project.lsh.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.project.lsh.beans.CommentBean;
import com.project.lsh.beans.PageBean;
import com.project.lsh.beans.UserBean;
import com.project.lsh.dao.CommentDao;

@Service
@PropertySource("/WEB-INF/properties/option.properties")
public class CommentService {
	@Resource(name = "loginUserBean")
	private UserBean loginUserBean;
	@Value("${page.commentListCnt}")
	private int commentListCnt;
	@Value("${page.commentPaginationCnt}")
	private int commentPaginationCnt;
	@Autowired
	private CommentDao commentDao;

	public int insertComment(CommentBean commentBean) {
		commentBean.setComment_writer_idx(loginUserBean.getUser_idx());
		int content_idx = commentBean.getContent_idx();
		int comment_parent_idx = commentBean.getComment_parent_idx();
		
		if (comment_parent_idx == 0) {
			commentDao.updateCommentCnt(content_idx);
		} else {
			commentDao.updateReplyCnt(content_idx, comment_parent_idx);
		}
		
		return commentDao.insertComment(commentBean);
	}
	
	public List<CommentBean> selectCommentList(int content_idx, int comment_parent_idx, int page) {
		int user_idx = loginUserBean.getUser_idx();
		int start = (page - 1) * commentListCnt;
		RowBounds rowBounds = new RowBounds(start, commentListCnt);
		List<CommentBean> commentList = commentDao.selectCommentList(content_idx, comment_parent_idx, user_idx, rowBounds);
		
		return commentList;
	}
	
	public String selectCommentText(int comment_idx) {
		return commentDao.selectCommentText(comment_idx);
	}
	
	public int selectReplyCnt(int content_idx, int comment_parent_idx) {
		return commentDao.selectCommentCnt(content_idx, comment_parent_idx);
	}
	
	public PageBean createPaging(int content_idx, int comment_parent_idx, int currentPage) {
		int commentCnt = commentDao.selectCommentCnt(content_idx, comment_parent_idx);
		PageBean pageBean = new PageBean(commentCnt, currentPage, commentListCnt, commentPaginationCnt);
		
		return pageBean;
	}
	
	public void updateComment(CommentBean commentBean) {
		commentDao.updateComment(commentBean);
	}
	
	public void updateCommentDeleteStatus(int comment_idx) {
		commentDao.updateCommentDeleteStatus(comment_idx);
	}
	
	public int selectLastPage(int content_idx, int comment_parent_idx) {
		int commentCnt = commentDao.selectCommentCnt(content_idx, comment_parent_idx);
		
		// 전체 페이지 개수
		int pageCnt = commentCnt / commentListCnt;
		if (commentCnt % commentListCnt > 0) {
			pageCnt++;
		}
		
		return pageCnt;
	}
}
