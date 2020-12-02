package com.project.lsh.beans;

public class LikeBean {
    private int like_idx; 
    private String like_obj_code; 
    private int content_idx; 
    private int comment_idx;
    private int user_idx;
    private String like_flag;
    
	public int getLike_idx() {
		return like_idx;
	}
	public void setLike_idx(int like_idx) {
		this.like_idx = like_idx;
	}
	public String getLike_obj_code() {
		return like_obj_code;
	}
	public void setLike_obj_code(String like_obj_code) {
		this.like_obj_code = like_obj_code;
	}
	public int getContent_idx() {
		return content_idx;
	}
	public void setContent_idx(int content_idx) {
		this.content_idx = content_idx;
	}
	public int getComment_idx() {
		return comment_idx;
	}
	public void setComment_idx(int comment_idx) {
		this.comment_idx = comment_idx;
	}
	public int getUser_idx() {
		return user_idx;
	}
	public void setUser_idx(int user_idx) {
		this.user_idx = user_idx;
	}
	public String getLike_flag() {
		return like_flag;
	}
	public void setLike_flag(String like_flag) {
		this.like_flag = like_flag;
	}
}
