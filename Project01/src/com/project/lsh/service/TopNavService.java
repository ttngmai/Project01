package com.project.lsh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.lsh.beans.BoardBean;
import com.project.lsh.dao.TopNavDao;

@Service
public class TopNavService {
	@Autowired
	private TopNavDao topNavDao;
	
	public List<BoardBean> selectTopNavList() {
		return topNavDao.selectTopNavList();
	}
}
