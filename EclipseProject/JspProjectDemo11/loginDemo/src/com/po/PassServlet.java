package com.po;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@SuppressWarnings("serial")
public class PassServlet extends HttpServlet {



public void doGet(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
       request.setCharacterEncoding("UTF-8");
       String name=request.getParameter("name");
       String pwd=request.getParameter("pwd");
       String sex=request.getParameter("sex");
       String age=request.getParameter("age");
       String email=request.getParameter("email");
       UserInfo user=new UserInfo();
       user.setUserName(name);
       user.setUserPwd(pwd);
       user.setUserSex(sex);
       user.setUserAge(Integer.parseInt(age));
       user.setUserEmail(email);
       request.setAttribute("user", user);
       request.setAttribute("shiwei", "wang");
       HttpSession session = request.getSession();
       session.setAttribute("shiwei2", "22222");
       response.sendRedirect("hu/shiwei.jsp");
//       request.getRequestDispatcher("shiwei.jsp").forward(request, response);
}
public void doPost(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
doGet(request,response);
}
}
