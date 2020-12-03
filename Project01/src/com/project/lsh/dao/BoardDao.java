package com.project.lsh.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.lsh.beans.BoardBean;
import com.project.lsh.beans.ContentBean;
import com.project.lsh.mapper.BoardMapper;

@Repository
public class BoardDao {
	@Autowired
	private BoardMapper boardMapper;
	
	public List<BoardBean> selectBoardList() {
		return boardMapper.selectBoardList();
	}
	
	public String selectBoardName(int board_idx) {
		return boardMapper.selectBoardName(board_idx);
	}
	
	public int selectContentCnt(int board_idx) {
		return boardMapper.selectContentCnt(board_idx);
	}
	
	public int selectContentCntForSearch(int board_idx, String searchType, String searchKeyword) {
		return boardMapper.selectContentCntForSearch(board_idx, searchType, searchKeyword);
	}
	
	public void insertContent(ContentBean contentBean) {
		boardMapper.insertContent(contentBean);
	}
	
	public List<ContentBean> selectContentList(int board_idx, RowBounds rowBounds) {
		return boardMapper.selectContentList(board_idx, rowBounds);
	}
	
	public List<ContentBean> selectContentListForSearch(int board_idx, String searchType, String searchKeyword, RowBounds rowBounds) {
		return boardMapper.selectContentListForSearch(board_idx, searchType, searchKeyword, rowBounds);
	}
	
	public ContentBean selectContent(int content_idx, int user_idx) {
		return boardMapper.selectContent(content_idx, user_idx);
	}
	
	public ContentBean selectContentLikeCnt(int content_idx) {
		return boardMapper.selectContentLikeCnt(content_idx);
	}
	
	public int selectCommentCnt(int content_idx) {
		return boardMapper.selectCommentCnt(content_idx);
	}
	
	public int updateContent(ContentBean contentBean) {
		return boardMapper.updateContent(contentBean);
	}
	
	public int deleteContent(int content_idx) {
		return boardMapper.deleteContent(content_idx);
	}
}
