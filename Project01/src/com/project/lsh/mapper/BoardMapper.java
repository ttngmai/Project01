package com.project.lsh.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.session.RowBounds;

import com.project.lsh.beans.BoardBean;
import com.project.lsh.beans.ContentBean;

public interface BoardMapper {
	@Select("select board_idx, board_name " +
			"from board_table " +
			"order by board_idx")
	List<BoardBean> selectBoardList();
	
	@Select("select board_name " +
			"from board_table " +
			"where board_idx = #{board_idx}")
	String selectBoardName(@Param("board_idx") int board_idx);
	
	@Select("select count(*) " +
			"from content_table " +
			"where board_idx = #{board_idx}")
	int selectContentCnt(@Param("board_idx") int board_idx);
	
	@Select("<script>" +
			"select count(*)" + 
			"from content_table a1, user_table a2 " + 
			"where a1.board_idx = #{board_idx}" + 
			"      and a1.content_writer_idx = a2.user_idx" +
			"      <if test=\"searchType == 'all'\">" +
			"      and (a1.content_subject like concat('%', #{searchKeyword}, '%')" +
			"      or a1.content_text like concat('%', #{searchKeyword}, '%')" +
			"      or a2.user_name like concat('%', #{searchKeyword}, '%')) " +
			"      </if>" +
			"      <if test=\"searchType == 'content_subject'\">" +
			"      and a1.content_subject like concat('%', #{searchKeyword}, '%') " +
			"      </if>" +
			"      <if test=\"searchType == 'content_text'\">" +
			"      and a1.content_text like concat('%', #{searchKeyword}, '%') " +
			"      </if>" +
			"      <if test=\"searchType == 'content_writer_name'\">" +
			"      and a2.user_name like concat('%', #{searchKeyword}, '%') " +
			"      </if>" +
			"</script>")
	int selectContentCntForSearch(@Param("board_idx") int board_idx, @Param("searchType") String searchType, @Param("searchKeyword") String searchKeyword);
	
	@SelectKey(statement = "select last_insert_id()", keyProperty = "content_idx", before = false, resultType = int.class)
	@Insert("insert into content_table (board_idx, content_writer_idx," +
			"                           content_subject, content_text, content_file," +
			"                           content_date) " +
			"values (#{board_idx}, #{content_writer_idx}," +
			"        #{content_subject}, #{content_text}, nullif(#{content_file, jdbcType=VARCHAR}, '')," +
			"        now())")
	void insertContent(ContentBean contentBean);
	
	@Select("select a1.content_idx, a2.user_name as content_writer_name, a1.content_subject," + 
			"       date_format(a1.content_date, '%Y-%m-%d') as content_date " + 
			"from content_table a1, user_table a2 " + 
			"where a1.board_idx = #{board_idx}" + 
			"      and a1.content_writer_idx = a2.user_idx " + 
			"order by a1.content_idx desc")
	List<ContentBean> selectContentList(@Param("board_idx") int board_idx, RowBounds rowBounds);
	
	@Select("<script>" +
			"select a1.content_idx, a2.user_name as content_writer_name, a1.content_subject," + 
			"       date_format(a1.content_date, '%Y-%m-%d') as content_date " + 
			"from content_table a1, user_table a2 " + 
			"where a1.board_idx = #{board_idx}" + 
			"      and a1.content_writer_idx = a2.user_idx" +
			"      <if test=\"searchType == 'all'\">" +
			"      and (a1.content_subject like concat('%', #{searchKeyword}, '%')" +
			"      or a1.content_text like concat('%', #{searchKeyword}, '%')" +
			"      or a2.user_name like concat('%', #{searchKeyword}, '%')) " +
			"      </if>" +
			"      <if test=\"searchType == 'content_subject'\">" +
			"      and a1.content_subject like concat('%', #{searchKeyword}, '%') " +
			"      </if>" +
			"      <if test=\"searchType == 'content_text'\">" +
			"      and a1.content_text like concat('%', #{searchKeyword}, '%') " +
			"      </if>" +
			"      <if test=\"searchType == 'content_writer_name'\">" +
			"      and a2.user_name like concat('%', #{searchKeyword}, '%') " +
			"      </if>" +
			"order by a1.content_idx desc" +
			"</script>")
	List<ContentBean> selectContentListForSearch(@Param("board_idx") int board_idx, 
												 @Param("searchType") String searchType, @Param("searchKeyword") String searchKeyword,
												 RowBounds rowBounds);
	
	@Select("select b1.content_idx, b1.content_writer_idx," + 
			"       b1.user_name as content_writer_name, b1.user_profile_img as content_writer_profile_img," + 
			"	    b1.content_subject, b1.content_text, b1.content_file," + 
			"	    date_format(b1.content_date, '%Y-%m-%d') as content_date," +
			"       b1.content_comment_cnt as comment_cnt," +
			"	    b2.like_flag," +
			"       case when b3.like_cnt is not null then b3.like_cnt else 0 end as like_cnt," + 
			"       case when b3.dislike_cnt is not null then b3.dislike_cnt else 0 end as dislike_cnt " +
			"from (" + 
			"    select *" + 
			"    from content_table a1, user_table a2" + 
			"    where a1.content_writer_idx = a2.user_idx" + 
			"		   and content_idx = #{content_idx}" + 
			") b1 " + 
			"left outer join (" + 
			"    select content_idx, like_flag" + 
			"    from like_table" + 
			"    where user_idx = #{user_idx}" + 
			"          and content_idx = #{content_idx}" + 
			"          and like_obj_code = 'CTT'" + 
			") b2 " + 
			"on b1.content_idx = b2.content_idx " + 
			"left outer join (" + 
			"    select content_idx," + 
			"           count(case when like_flag = 'L' then 1 end) as like_cnt," + 
			"           count(case when like_flag = 'D' then 1 end) as dislike_cnt" + 
			"    from like_table" + 
			"    where like_obj_code = 'CTT'" + 
			"    group by content_idx" + 
			") b3 " + 
			"on b1.content_idx = b3.content_idx")
	ContentBean selectContent(@Param("content_idx")int content_idx, @Param("user_idx")int user_idx);
	
	@Select("select count(case when like_flag = 'L' then 1 end) as like_cnt," +
			"       count(case when like_flag = 'D' then 1 end) as dislike_cnt " + 
			"from like_table " + 
			"where content_idx = #{content_idx}" +
			"	   and like_obj_code = 'CTT'")
	ContentBean selectContentLikeCnt(@Param("content_idx") int content_idx);
	
	@Select("select content_comment_cnt " +
			"from content_table " +
			"where content_idx = #{content_idx}")
	int selectCommentCnt(@Param("content_idx") int content_idx);
	
	@Update("update content_table " +
			"set content_subject = #{content_subject}, content_text = #{content_text}," +
			"	 content_file = nullif(#{content_file, jdbcType=VARCHAR}, '') " +
			"where content_idx = #{content_idx}")
	int updateContent(ContentBean contentBean);
	
	@Delete("delete from content_table " +
			"where content_idx = #{content_idx}")
	int deleteContent(@Param("content_idx") int content_idx);
}
