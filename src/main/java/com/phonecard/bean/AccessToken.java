package com.phonecard.bean;

 //令牌
public class AccessToken {
	//token
	private String token;
	//过期时间
	private int expiresIn;
	public String getToken() {
		return token;
	}
	public int getExpiresIn() {
		return expiresIn;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}
}
