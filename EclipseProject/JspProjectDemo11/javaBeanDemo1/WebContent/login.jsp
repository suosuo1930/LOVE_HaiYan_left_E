<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
    <jsp:useBean id="date" class="com.table.login"></jsp:useBean>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>javaBean</title>
<link rel="stylesheet" href="css/login.css">

</head>
<body>
	<div>
		<form action="" method="post">
			账号<input type="text" name="username" id="one"><br>
			密码<input type="password"  name="password" id="two"><br>
			<input type="submit"  name="submit" value="提交" id="three">
			<input type="reset" name="reset" value="重置" id="four">
		</form>
</div>
 <%
String username = request.getParameter("username");
String password = request.getParameter("password");
date.setUsername("shiwei22");
date.setPassword("123456");
if(username == null || password == null){
	username = "";
	password = "";

}
else if(username.equals(date.getUsername()) && password.equals(date.getPassword())){
%>
<script type="text/javascript">
alert("恭喜你， 登录成功！");
</script>

<%} 
else{
	out.println("都错了！");
}

%>

</body>
</html>