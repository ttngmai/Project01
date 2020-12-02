package com.project.lsh.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.lsh.beans.BoardBean;
import com.project.lsh.beans.ContentBean;
import com.project.lsh.dao.BoardDao;

@Service
public class MainService {
	@Autowired
	private BoardDao boardDao;
	
	public List<BoardBean> getBoardList() {
		return boardDao.selectBoardList();
	}
	
	public List<ContentBean> getMainContentList(int board_idx) {
		RowBounds rowBounds = new RowBounds(0, 5);
		
		return boardDao.selectContentList(board_idx, rowBounds);
	}
}
