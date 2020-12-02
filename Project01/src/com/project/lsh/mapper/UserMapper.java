package com.project.lsh.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.project.lsh.beans.UserBean;

public interface UserMapper {
	@Select("select user_name " +
			"from user_table " +
			"where user_id = #{user_id}")
	String checkUserIdExist(@Param("user_id") String user_id);
	
	@SelectKey(statement = "select last_insert_id()", keyProperty = "user_idx", before = false, resultType = int.class)
	@Insert("insert into user_table (user_idx, user_name, user_id, user_pw, user_email, user_introduction) " +
			"values (#{user_idx}, #{user_name}, #{user_id}, #{user_pw}, #{user_email}, '안녕하세요!')")
	void insertUser(UserBean userBean);
	
	@Update("update user_table " +
			"set user_auth_status = 'Y' " +
			"where user_idx = #{user_idx}")
	void updateUserAuthStatus(@Param("user_idx") int user_idx);
	
	@Select("select user_idx, user_name, user_auth_status " +
			"from user_table " +
			"where user_id = #{user_id} and user_pw = #{user_pw}")
	UserBean selectUserForLogin(UserBean userBean);
	
	@Select("select user_name, user_email, user_profile_img, user_introduction " +
			"from user_table " +
			"where user_idx = #{user_idx}")
	UserBean selectUser(@Param("user_idx") int user_idx);
	
	@Update("update user_table " +
			"set user_profile_img = nullif(#{user_profile_img, jdbcType=VARCHAR}, '')," +
			"    user_introduction = #{user_introduction} " +
			"where user_idx = #{user_idx}")
	void updateUserProfileImg(UserBean userBean);
	
	@Update("update user_table " +
			"set user_pw = #{user_pw} " +
			"where user_idx = #{user_idx}")
	void updateUserPassword(UserBean userBean);
	
	@Update("update user_table " +
			"set user_email = #{user_email}, user_auth_status = 'Y' " +
			"where user_idx = #{user_idx}")
	void updateUserEmail(@Param("user_idx") int user_idx, @Param("user_email") String user_email);
}
