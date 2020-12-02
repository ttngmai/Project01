package com.project.lsh.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.project.lsh.beans.AuthBean;

public interface AuthMapper {
	@Insert("insert into auth_table (user_idx, auth_key) " +
			"values (#{user_idx}, #{auth_key})")
	void insertAuthKey(AuthBean authBean);
	
	@Select("select a1.user_idx " + 
			"from user_table a1, auth_table a2 " + 
			"where a1.user_idx = a2.user_idx" +
			"	   and a1.user_email = #{user_email} and a2.auth_key = #{auth_key}")
	String selectAuthUserIdxForJoin(@Param("user_email") String user_email, @Param("auth_key") String auth_key);
	
	@Select("select a1.user_idx " +
			"from user_table a1, auth_table a2 " +
			"where a1.user_idx = a2.user_idx" +
			"	   and a2.auth_key = #{auth_key}")
	String selectAuthUserIdxForUpdate(@Param("auth_key") String auth_key);
	
	
	@Delete("delete from auth_table " +
			"where user_idx = #{user_idx}")
	void deleteAuthKeyBeforeSendMail(@Param("user_idx") int user_idx);
	
	@Delete("delete from auth_table " +
			"where auth_key = #{auth_key}")
	void deleteAuthKeyAfterCheckMail(@Param("auth_key") String auth_key);
}
