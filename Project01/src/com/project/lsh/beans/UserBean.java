package com.project.lsh.beans;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import com.project.lsh.validator.ValidationGroups;

public class UserBean {
    private int user_idx; 
    @Size(min = 2, max = 4)
    @Pattern(regexp = "[가-힣]*")
    private String user_name;
    @Size(min = 4, max = 20, groups = {ValidationGroups.PasswordGroup.class})
    @Pattern(regexp = "[a-zA-Z0-9]*", groups = {ValidationGroups.PasswordGroup.class})
    private String user_id ;
    @Size(min = 4, max = 12, groups = {ValidationGroups.PasswordGroup.class})
    @Pattern(regexp = "[a-zA-Z0-9]*", groups = {ValidationGroups.PasswordGroup.class})
    private String user_pw;
    @Size(min = 4, max = 12)
    @Pattern(regexp = "[a-zA-Z0-9]*")
    private String user_pw2;
    @NotBlank(groups = {ValidationGroups.EmailGroup.class})
    @Email(groups = {ValidationGroups.EmailGroup.class})
    private String user_email;
    private MultipartFile upload_profile_img;    // 클라이언트가 보낸 file 데이터를 담을 변수
    private String user_profile_img;			 // 서버에 저장되어지는 file 이름을 담을 변수
    private String user_introduction;
    private String user_auth_status;
    private boolean userIdExist;
    private boolean userLogin;
    
    public UserBean() {
    	this.userIdExist = false;
    	this.userLogin = false;
    }
    
	public int getUser_idx() {
		return user_idx;
	}
	public void setUser_idx(int user_idx) {
		this.user_idx = user_idx;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_pw() {
		return user_pw;
	}
	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
	}
	public String getUser_pw2() {
		return user_pw2;
	}
	public void setUser_pw2(String user_pw2) {
		this.user_pw2 = user_pw2;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public MultipartFile getUpload_profile_img() {
		return upload_profile_img;
	}
	public void setUpload_profile_img(MultipartFile upload_profile_img) {
		this.upload_profile_img = upload_profile_img;
	}
	public String getUser_profile_img() {
		return user_profile_img;
	}
	public void setUser_profile_img(String user_profile_img) {
		this.user_profile_img = user_profile_img;
	}
	public String getUser_introduction() {
		return user_introduction;
	}
	public void setUser_introduction(String user_introduction) {
		this.user_introduction = user_introduction;
	}
	public String getUser_auth_status() {
		return user_auth_status;
	}
	public void setUser_auth_status(String user_auth_status) {
		this.user_auth_status = user_auth_status;
	}
	public boolean isUserIdExist() {
		return userIdExist;
	}
	public void setUserIdExist(boolean userIdExist) {
		this.userIdExist = userIdExist;
	}
	public boolean isUserLogin() {
		return userLogin;
	}
	public void setUserLogin(boolean userLogin) {
		this.userLogin = userLogin;
	}
}
