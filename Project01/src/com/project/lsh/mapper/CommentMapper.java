package com.project.lsh.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.session.RowBounds;

import com.project.lsh.beans.CommentBean;

public interface CommentMapper {
	@Insert("insert into comment_table (comment_parent_idx, content_idx," + 
			"							comment_writer_idx, comment_text, comment_date) " + 
			"values (#{comment_parent_idx}, #{content_idx}," +
			"		 #{comment_writer_idx}, #{comment_text}, now())")
	void insertComment(CommentBean commentBean);
	
	@Update("update content_table " +
			"set content_comment_cnt = content_comment_cnt + 1 " + 
			"where content_idx = #{content_idx}")
	void updateCommentCnt(@Param("content_idx") int content_idx);
	
	@Update("update comment_table " + 
			"set comment_reply_cnt = comment_reply_cnt + 1 " + 
			"where content_idx = #{content_idx}" + 
			"      and comment_idx = #{comment_parent_idx}")
	void updateReplyCnt(@Param("content_idx") int content_idx, @Param("comment_parent_idx") int comment_parent_idx);
	
	@Select("select b1.comment_idx, b1.comment_parent_idx, b1.content_idx," + 
			"       b1.comment_writer_idx, b1.user_name as comment_writer_name," + 
			"       case when b1.comment_delete_status = 'Y' then '--삭제된 댓글 입니다--' else b1.comment_text end as comment_text," + 
			"       b1.comment_date, b1.comment_delete_status, b1.comment_reply_cnt as reply_cnt," +
			"       b2.like_flag," +
			"       case when b3.like_cnt is not null then b3.like_cnt else 0 end as like_cnt," + 
			"       case when b3.dislike_cnt is not null then b3.dislike_cnt else 0 end as dislike_cnt " + 
			"from (" + 
			"    select *" + 
			"    from comment_table a1, user_table a2" + 
			"    where a1.comment_writer_idx = a2.user_idx" + 
			"          and content_idx = #{content_idx}" + 
			"          and comment_parent_idx = #{comment_parent_idx}" + 
			"    order by comment_idx asc" + 
			") b1 " + 
			"left outer join (" + 
			"    select comment_idx, like_flag" + 
			"    from like_table" + 
			"    where user_idx = #{user_idx}" + 
			"          and content_idx = #{content_idx}" +
			"          and like_obj_code = 'CMT'" +
			") b2 " + 
			"on b1.comment_idx = b2.comment_idx " + 
			"left outer join (" + 
			"    select comment_idx," + 
			"           count(case when like_flag = 'L' then 1 end) as like_cnt," + 
			"           count(case when like_flag = 'D' then 1 end) as dislike_cnt" + 
			"    from like_table" + 
			"    where like_obj_code = 'CMT'" + 
			"    group by comment_idx" + 
			") b3 " + 
			"on b1.comment_idx = b3.comment_idx")
	List<CommentBean> selectCommentList(@Param("content_idx") int content_idx, @Param("comment_parent_idx") int comment_parent_idx, @Param("user_idx") int user_idx,
										RowBounds rowBounds);
	
	@Select("select b1.comment_idx, b1.comment_parent_idx, b1.content_idx," + 
			"       b1.comment_writer_idx, b1.user_name as comment_writer_name," + 
			"       case when b1.comment_delete_status = 'Y' then '--삭제된 댓글 입니다--' else b1.comment_text end as comment_text," + 
			"       b1.comment_date, b1.comment_delete_status, b1.comment_reply_cnt as reply_cnt," +
			"       b2.like_flag," +
			"       case when b3.like_cnt is not null then b3.like_cnt else 0 end as like_cnt," + 
			"       case when b3.dislike_cnt is not null then b3.dislike_cnt else 0 end as dislike_cnt " + 
			"from (" + 
			"    select *" + 
			"    from comment_table a1, user_table a2" + 
			"    where a1.comment_idx = #{comment_idx}" +
			"          and a1.comment_writer_idx = a2.user_idx" +
			") b1 " + 
			"left outer join (" + 
			"    select comment_idx, like_flag" + 
			"    from like_table" + 
			"    where user_idx = #{user_idx}" +
			"          and comment_idx = #{comment_idx}" +
			") b2 " + 
			"on b1.comment_idx = b2.comment_idx " + 
			"left outer join (" + 
			"    select comment_idx," + 
			"           count(case when like_flag = 'L' then 1 end) as like_cnt," + 
			"           count(case when like_flag = 'D' then 1 end) as dislike_cnt" + 
			"    from like_table" + 
			"    where comment_idx = #{comment_idx} " +
			") b3 " + 
			"on b1.comment_idx = b3.comment_idx")
	CommentBean selectComment(@Param("comment_idx") int comment_idx, @Param("user_idx") int user_idx);
	
	@Select("select comment_text " +
			"from comment_table " +
			"where comment_idx = #{comment_idx}")
	String selectCommentText(@Param("comment_idx") int comment_idx);
	
	@Select("select count(case when like_flag = 'L' then 1 end) as like_cnt," +
			"       count(case when like_flag = 'D' then 1 end) as dislike_cnt " + 
			"from like_table " + 
			"where comment_idx = #{comment_idx}" +
			"	   and like_obj_code = 'CMT'")
	CommentBean selectCommentLikeCnt(@Param("comment_idx") int comment_idx);
	
	@Select("select count(*) " +
			"from comment_table " +
			"where content_idx = #{content_idx}" +
			"      and comment_parent_idx = #{comment_parent_idx}")
	int selectCommentCnt(@Param("content_idx") int content_idx, @Param("comment_parent_idx") int comment_parent_idx);
	
	@Update("update comment_table " + 
			"set comment_text = #{comment_text} " + 
			"where comment_idx = #{comment_idx}")
	void updateComment(CommentBean commentBean);
	
	@Update("update comment_table " + 
			"set comment_delete_status = 'Y' " + 
			"where comment_idx = #{comment_idx}")
	void updateCommentDeleteStatus(@Param("comment_idx") int comment_idx);
}
