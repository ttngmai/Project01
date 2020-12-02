package com.project.lsh.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.lsh.beans.AuthBean;
import com.project.lsh.mapper.AuthMapper;

@Repository
public class AuthDao {
	@Autowired
	private AuthMapper authMapper;
	
	public void addAuthKey(AuthBean authBean) {
		authMapper.insertAuthKey(authBean);
	}
	
	public String selectAuthUserIdxForJoin(String user_email, String auth_key) {
		return authMapper.selectAuthUserIdxForJoin(user_email, auth_key);
	}
	
	public String selectAuthUserIdxForUpdate(String auth_key) {
		return authMapper.selectAuthUserIdxForUpdate(auth_key);
	}
	
	public void deleteAuthKeyBeforeSendMail(int user_idx) {
		authMapper.deleteAuthKeyBeforeSendMail(user_idx);
	}
	
	public void deleteAuthKeyAfterCheckMail(String auth_key) {
		authMapper.deleteAuthKeyAfterCheckMail(auth_key);
	}
	
}
