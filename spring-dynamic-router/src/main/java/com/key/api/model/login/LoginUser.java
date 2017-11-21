package com.key.api.model.login;

import java.io.Serializable;

/**
 * 登录用户
 * @author zhang
 *
 */
public class LoginUser implements Serializable  {
	private static final long serialVersionUID = 1L;
	
	/*
	 * 主键
	 */
	private int id;
	
	/**
	 * 用户名
	 */
	private String user_Name;
	
	/**
	 * 密码
	 */
	private String user_Password;
	
	LoginUser() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return user_Name;
	}

	public void setUserName(String userName) {
		this.user_Name = userName;
	}

	public String getUserPassword() {
		return user_Password;
	}

	public void setUserPassword(String userPassword) {
		this.user_Password = userPassword;
	}
	
//	@Override
//	public String toString() {
//		return "LoginUser [id=" + id + ", name=" + user_Name + ", password=" + user_Password + "]";
//	}

}
