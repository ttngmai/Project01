package com.project.lsh.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.lsh.beans.UserBean;
import com.project.lsh.dao.AuthDao;
import com.project.lsh.dao.UserDao;

@Service
@PropertySource("/WEB-INF/properties/page.properties")
@PropertySource("/WEB-INF/properties/aws.properties")
public class UserService {
	@Resource(name = "loginUserBean")
	private UserBean loginUserBean;
	@Value("${s3.bucketKey}")
	private String s3_bucketKey;
	@Autowired
	private AwsService awsService;
	@Autowired
	private UserDao userDao;
	@Autowired
	private AuthDao authDao;
	
	public boolean checkUserIdExist(String user_id) {
		String user_name = userDao.checkUserIdExist(user_id);
		
		if (user_name == null) {
			return true;
		}
		
		return false; 
	}
	
	public boolean checkValidAuthKeyForJoin(String user_email, String auth_key) {
		String user_idx = authDao.selectAuthUserIdxForJoin(user_email, auth_key);
		
		if (user_idx != null) {
			userDao.updateUserAuthStatus(Integer.parseInt(user_idx));
			
			return true;
		}
		
		return false;
	}
	
	public void insertUser(UserBean userBean) {
		userDao.insertUser(userBean);
	}
	
	public void selectUserForLogin(UserBean userBean) {
		UserBean tempUserBean = userDao.selectUserForLogin(userBean);
		
		if (tempUserBean != null) {
			loginUserBean.setUser_idx(tempUserBean.getUser_idx());
			loginUserBean.setUser_name(tempUserBean.getUser_name());
			loginUserBean.setUser_auth_status(tempUserBean.getUser_auth_status());
			loginUserBean.setUserLogin(true);
		}
	}
	
	public void selectUser(UserBean userBean) {
		UserBean tempUserBean = userDao.selectUser(loginUserBean.getUser_idx());
		
		if (tempUserBean != null) {
			userBean.setUser_name(tempUserBean.getUser_name());
			userBean.setUser_email(tempUserBean.getUser_email());
			userBean.setUser_profile_img(tempUserBean.getUser_profile_img());
			userBean.setUser_introduction(tempUserBean.getUser_introduction());
		}
	}
	
	public void updateUserProfileImg(UserBean userBean) {
		userBean.setUser_idx(loginUserBean.getUser_idx());
		
		MultipartFile upload_profile_img = userBean.getUpload_profile_img();
		
		if (upload_profile_img.getSize() > 0) {
			String profile_img_name = awsService.uploadMultipartFile(upload_profile_img, s3_bucketKey);
			
			userBean.setUser_profile_img(profile_img_name);
		}
		
		userDao.updateUserProfileImg(userBean);
	}
	
	public void updateUserPassword(UserBean updateUserBean) {
		updateUserBean.setUser_idx(loginUserBean.getUser_idx());
		
		userDao.updateUserPassword(updateUserBean);
	}
	
	public boolean checkValidAuthKeyForUpdate(String user_email, String auth_key) {
		String user_idx = authDao.selectAuthUserIdxForUpdate(auth_key);
		
		if (user_idx != null) {
			userDao.updateUserEmail(Integer.parseInt(user_idx), user_email);
			
			return true;
		}
		
		return false;
	}
}
