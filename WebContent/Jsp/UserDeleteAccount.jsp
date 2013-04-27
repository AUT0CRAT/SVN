<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="DBConnection.DBConnection"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<body>

<%
	try
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DBConnection.getConnection();
		String sql=null;
		PreparedStatement ps=null;
		
		sql = "delete from login where user_id = ?";
		ps = con.prepareStatement(sql);
		ps.setInt(1, Integer.parseInt(session.getAttribute("user_id")+""));
		ps.executeUpdate();
		
		response.sendRedirect("UserLogin.jsp");
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}

%>

</body>
</html>