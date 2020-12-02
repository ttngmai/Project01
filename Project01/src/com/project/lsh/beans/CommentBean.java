package com.project.lsh.beans;

import javax.validation.constraints.NotBlank;

public class CommentBean {
    private int comment_idx;
    private int comment_parent_idx;
    private int content_idx;
    private int comment_writer_idx;
    private String comment_writer_name;
    @NotBlank
    private String comment_text;
    private String comment_date;
    private String comment_delete_status;
    private String like_flag;
    private int like_cnt;
    private int dislike_cnt;
    private int reply_cnt;
    
	public int getComment_idx() {
		return comment_idx;
	}
	public void setComment_idx(int comment_idx) {
		this.comment_idx = comment_idx;
	}
	public int getComment_parent_idx() {
		return comment_parent_idx;
	}
	public void setComment_parent_idx(int comment_parent_idx) {
		this.comment_parent_idx = comment_parent_idx;
	}
	public int getContent_idx() {
		return content_idx;
	}
	public void setContent_idx(int content_idx) {
		this.content_idx = content_idx;
	}
	public int getComment_writer_idx() {
		return comment_writer_idx;
	}
	public void setComment_writer_idx(int comment_writer_idx) {
		this.comment_writer_idx = comment_writer_idx;
	}
	public String getComment_writer_name() {
		return comment_writer_name;
	}
	public void setComment_writer_name(String comment_writer_name) {
		this.comment_writer_name = comment_writer_name;
	}
	public String getComment_text() {
		return comment_text;
	}
	public void setComment_text(String comment_text) {
		this.comment_text = comment_text;
	}
	public String getComment_date() {
		return comment_date;
	}
	public void setComment_date(String comment_date) {
		this.comment_date = comment_date;
	}
	public String getComment_delete_status() {
		return comment_delete_status;
	}
	public void setComment_delete_status(String comment_delete_status) {
		this.comment_delete_status = comment_delete_status;
	}
	public String getLike_flag() {
		return like_flag;
	}
	public void setLike_flag(String like_flag) {
		this.like_flag = like_flag;
	}
	public int getLike_cnt() {
		return like_cnt;
	}
	public void setLike_cnt(int like_cnt) {
		this.like_cnt = like_cnt;
	}
	public int getDislike_cnt() {
		return dislike_cnt;
	}
	public void setDislike_cnt(int dislike_cnt) {
		this.dislike_cnt = dislike_cnt;
	}
	public int getReply_cnt() {
		return reply_cnt;
	}
	public void setReply_cnt(int reply_cnt) {
		this.reply_cnt = reply_cnt;
	}
}
