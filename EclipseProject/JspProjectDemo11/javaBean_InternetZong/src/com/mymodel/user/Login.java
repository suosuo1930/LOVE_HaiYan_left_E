package com.mymodel.user;

public class Login {
	private String userName;
	private String password;
	public Login(){}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
		public boolean check(){
		if(userName.equals("lantian")&&(password.equals("123456"))){
			return true;
		}else {
			return false;
		}
	}
}
