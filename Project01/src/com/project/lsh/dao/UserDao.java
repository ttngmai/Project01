package com.project.lsh.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.lsh.beans.UserBean;
import com.project.lsh.mapper.UserMapper;

@Repository
public class UserDao {
	@Autowired
	private UserMapper userMapper;
	
	public String checkUserIdExist(String user_id) {
		return userMapper.checkUserIdExist(user_id);
	}
	
	public void insertUser(UserBean userBean) {
		userMapper.insertUser(userBean);
	}
	
	public void updateUserAuthStatus(int user_idx) {
		userMapper.updateUserAuthStatus(user_idx);
	}
	
	public UserBean selectUserForLogin(UserBean userBean) {
		return userMapper.selectUserForLogin(userBean);
	}
	
	public UserBean selectUser(int user_idx) {
		return userMapper.selectUser(user_idx);
	}
	
	public void updateUserProfileImg(UserBean userBean) {
		userMapper.updateUserProfileImg(userBean);
	}
	
	public void updateUserPassword(UserBean userBean) {
		userMapper.updateUserPassword(userBean);
	}
	
	public void updateUserEmail(int user_idx, String user_email) {
		userMapper.updateUserEmail(user_idx, user_email);
	}
}
