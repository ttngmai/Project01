package com.project.lsh.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.lsh.beans.BoardBean;
import com.project.lsh.mapper.TopNavMapper;

@Repository
public class TopNavDao {
	@Autowired
	private TopNavMapper topNavMapper;
	
	public List<BoardBean> selectTopNavList() {
		return topNavMapper.selectTopNavList();
	}
}
