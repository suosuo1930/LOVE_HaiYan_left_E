package com.table;

public class login {
  String username = "";
  String password = "";
public login() {

}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}


public  boolean  check(){
if(username == "" || password == ""){
	return false;
}else if(username == "shiwei" && password == "123456"){
	return true;
}else{
	return false;
}
}



	
	
}
