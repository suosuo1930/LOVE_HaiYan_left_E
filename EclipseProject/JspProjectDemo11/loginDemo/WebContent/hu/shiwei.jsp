<%@page import="java.sql.Time"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%String shi = (String)session.getAttribute("shiwei2");

out.println(shi);

%>
<script type="text/javascript">
alert("wang222");
</script>
<%
request.setAttribute("wang","wang");
Thread.sleep(2000);
response.sendRedirect("wang.jsp");%>
</body>
</html>