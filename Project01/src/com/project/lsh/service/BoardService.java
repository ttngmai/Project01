package com.project.lsh.service;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.lsh.beans.ContentBean;
import com.project.lsh.beans.PageBean;
import com.project.lsh.beans.UserBean;
import com.project.lsh.dao.BoardDao;

@Service
@PropertySource("/WEB-INF/properties/option.properties")
public class BoardService {
	@Resource(name = "loginUserBean")
	private UserBean loginUserBean;
	@Value("${path.upload}")
	private String path_upload;
	@Value("${page.contentListCnt}")
	private int contentListCnt;
	@Value("${page.contentPaginationCnt}")
	private int contentPaginationCnt;
	@Autowired
	private BoardDao boardDao;
	
	public String selectBoardName(int board_idx) {
		return boardDao.selectBoardName(board_idx);
	}
	
	public PageBean selectContentCnt(int board_idx, String searchType, String searchKeyword, int page) {
		int content_cnt;
		String tempSearchType = searchType.trim();
		String tempSearchKeyword = searchKeyword.trim();
		
		if (!(tempSearchType.isEmpty() || tempSearchKeyword.isEmpty())) {
			content_cnt = boardDao.selectContentCntForSearch(board_idx, searchType, searchKeyword);
		} else {
			content_cnt = boardDao.selectContentCnt(board_idx);
		}
		
		PageBean pageBean = new PageBean(content_cnt, page, contentListCnt, contentPaginationCnt);
		
		return pageBean;
	}

	private String saveUploadFile(MultipartFile upload_file) {
		String file_name = System.currentTimeMillis() + "_" + upload_file.getOriginalFilename();
		
		try {
			upload_file.transferTo(new File(path_upload + "/" + file_name));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return file_name;
	}
	
	public void insertContent(ContentBean contentBean) {
		MultipartFile upload_file = contentBean.getUpload_file();
		
		if (upload_file.getSize() > 0) {
			String file_name = saveUploadFile(upload_file);
			contentBean.setContent_file(file_name);
		}
		
		contentBean.setContent_writer_idx(loginUserBean.getUser_idx());
		
		boardDao.insertContent(contentBean);
	}
	
	public List<ContentBean> selectContentList(int board_idx, String searchType, String searchKeyword, int page) {
		String tempSearchType = searchType.trim();
		String tempSearchKeyword = searchKeyword.trim();
		int start = (page - 1) * contentListCnt;
		RowBounds rowBounds = new RowBounds(start, contentListCnt);
		
		if (!(tempSearchType.isEmpty() || tempSearchKeyword.isEmpty())) {
			return boardDao.selectContentListForSearch(board_idx, tempSearchType, tempSearchKeyword, rowBounds);
		}
		
		return boardDao.selectContentList(board_idx, rowBounds);
	}
	
	public ContentBean selectContent(int content_idx) {
		int user_idx = loginUserBean.getUser_idx();
		ContentBean readContentBean = boardDao.selectContent(content_idx, user_idx);
		
		return readContentBean;
	}
	
	public int selectCommentCnt(int content_idx) {
		return boardDao.selectCommentCnt(content_idx);
	}
	
	public void updateContent(ContentBean contentBean) {
		MultipartFile upload_file = contentBean.getUpload_file();
		
		if (upload_file.getSize() > 0) {
			String file_name = saveUploadFile(upload_file);
			contentBean.setContent_file(file_name);
		}
		
		boardDao.updateContent(contentBean);
	}
	
	public void deleteContent(int content_idx) {
		boardDao.deleteContent(content_idx);
	}
}
