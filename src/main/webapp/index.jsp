<%@ page import="org.apache.ibatis.session.SqlSessionFactory" %>
<%@ page import="com.example.boardv02.sqlMap.SqlSessionManager" %>
<%@ page import="org.apache.ibatis.session.SqlSession" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %></h1>
<br/>
<a href="hello-servlet">Hello Servlet</a>
<%
  SqlSessionFactory sqlSessionFactory = SqlSessionManager.getSqlSession();
  SqlSession sqlSession = sqlSessionFactory.openSession();

  int cnt = 0;
  try{
    cnt = sqlSession.selectOne("Test.getOne");
  }
  catch (Exception e){
    e.printStackTrace();
  }finally {
    sqlSession.close();
  }
%>
<h1><%=cnt%></h1>
</body>
</html>