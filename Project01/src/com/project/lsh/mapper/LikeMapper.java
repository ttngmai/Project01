package com.project.lsh.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface LikeMapper {
	@Select ("select a1.like_flag " + 
			 "from (" + 
			 "    select like_flag" + 
			 "    from like_table" + 
			 "    where content_idx = #{content_idx}" +
			 "			and user_idx = #{user_idx}" +
			 "			and like_obj_code = 'CTT'" +
			 "    union all" + 
			 "    select null as like_flag" + 
			 ") a1 limit 1")
	String checkAlreadyLikeContent(@Param("content_idx") int content_idx, @Param("user_idx") int user_idx);
	
	@Insert ("insert into like_table (like_obj_code, content_idx, user_idx, like_flag) " + 
			 "values ('CTT', #{content_idx}, #{user_idx}, #{like_flag})")
	int insertLikeContent(@Param("content_idx") int content_idx, @Param("user_idx") int user_idx, @Param("like_flag") String like_flag);
	
	@Update ("update like_table " + 
			 "set like_flag = #{like_flag} " +
			 "where content_idx = #{content_idx} and user_idx = #{user_idx}")
	void updateLikeContent(@Param("content_idx") int content_idx, @Param("user_idx") int user_idx, @Param("like_flag") String like_flag);
	
	@Delete ("delete from like_table " +
			 "where content_idx = #{content_idx}" +
			 "      and user_idx = #{user_idx}")
	void deleteLikeContent(@Param("content_idx") int content_idx, @Param("user_idx") int user_idx);
	
	@Select ("select a1.like_flag " + 
			 "from (" + 
			 "    select like_flag" + 
			 "    from like_table" + 
			 "    where comment_idx = #{comment_idx} and user_idx = #{user_idx}" + 
			 "    union all" + 
			 "    select null as like_flag" + 
			 ") a1 limit 1")
	String checkAlreadyLikeComment(@Param("comment_idx") int comment_idx, @Param("user_idx") int user_idx);
	
	@Insert ("insert into like_table (like_obj_code, content_idx, comment_idx, user_idx, like_flag) " + 
			 "values ('CMT', #{content_idx}, #{comment_idx}, #{user_idx}, #{like_flag})")
	int insertLikeComment(@Param("content_idx") int content_idx, @Param("comment_idx") int comment_idx, @Param("user_idx") int user_idx,
						  @Param("like_flag") String like_flag);
	
	@Update ("update like_table " + 
			 "set like_flag = #{like_flag} " +
			 "where comment_idx = #{comment_idx} and user_idx = #{user_idx}")
	void updateLikeComment(@Param("comment_idx") int comment_idx, @Param("user_idx") int user_idx, @Param("like_flag") String like_flag);
	
	@Delete ("delete from like_table " +
			 "where comment_idx = #{comment_idx}" +
			 "      and user_idx = #{user_idx}")
	void deleteLikeComment(@Param("comment_idx") int comment_idx, @Param("user_idx") int user_idx);
}
