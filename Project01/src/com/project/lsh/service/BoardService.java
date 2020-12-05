package com.project.lsh.service;

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
@PropertySource("/WEB-INF/properties/page.properties")
@PropertySource("/WEB-INF/properties/aws.properties")
public class BoardService {
	@Resource(name = "loginUserBean")
	private UserBean loginUserBean;
	@Value("${s3.bucketKey}")
	private String s3_bucketKey;
	@Autowired
	private AwsService awsService;
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
	
	public void insertContent(ContentBean contentBean) {
		MultipartFile upload_file = contentBean.getUpload_file();
		
		if (upload_file.getSize() > 0) {
			String file_name = awsService.uploadMultipartFile(upload_file, s3_bucketKey);
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
		ContentBean readContentBean = boardDao.selectContent(content_idx, loginUserBean.getUser_idx());
		
		return readContentBean;
	}
	
	public int selectCommentCnt(int content_idx) {
		return boardDao.selectCommentCnt(content_idx);
	}
	
	public int updateContent(ContentBean contentBean) {
		MultipartFile upload_file = contentBean.getUpload_file();
		
		if (upload_file.getSize() > 0) {
			String file_name = awsService.uploadMultipartFile(upload_file, s3_bucketKey);
			contentBean.setContent_file(file_name);
		}
		
		return boardDao.updateContent(contentBean);
	}
	
	public int deleteContent(int content_idx) {
		return boardDao.deleteContent(content_idx);
	}
}
