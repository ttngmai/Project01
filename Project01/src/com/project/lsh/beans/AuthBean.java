package com.project.lsh.beans;

public class AuthBean {
	private int auth_idx;
	private int user_idx;
	private String auth_key;
	
	public int getAuth_idx() {
		return auth_idx;
	}
	public void setAuth_idx(int auth_idx) {
		this.auth_idx = auth_idx;
	}
	public int getUser_idx() {
		return user_idx;
	}
	public void setUser_idx(int user_idx) {
		this.user_idx = user_idx;
	}
	public String getAuth_key() {
		return auth_key;
	}
	public void setAuth_key(String auth_key) {
		this.auth_key = auth_key;
	}
}
