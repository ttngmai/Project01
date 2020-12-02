package com.project.lsh.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.project.lsh.beans.BoardBean;

public interface TopNavMapper {
	@Select("select board_idx, board_name " +
			"from board_table " +
			"order by board_idx")
	List<BoardBean> selectTopNavList();
}
