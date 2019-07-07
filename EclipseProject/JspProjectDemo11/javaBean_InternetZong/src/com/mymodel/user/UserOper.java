package com.mymodel.user;
import java.sql.*;

import com.db.DBConn;
public class UserOper {
	public boolean check(User user){
		if(user.getUserName().equals("lantian")&&(user.getPassword().equals("123456"))){
			return true;
		}else {
			return false;
		}
	} 
	public boolean login(User user){ 
        DBConn db=new DBConn();
        String sql="SELECT * FROM userlogin  WHERE username=? AND password=?";
	    String name=user.getUserName();
	    String pass=user.getPassword();
        try{
        	Connection conn=db.getConnection();
            PreparedStatement pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,name);
            pstmt.setString(2,pass);
            ResultSet rs=pstmt.executeQuery();
	        if(rs.next()) { 
	           return true;  
	        }else{ 
	           return false; 
	        }  
	     } catch(Exception ex){ 
	        return false;  
	     }  
	  } 
}
