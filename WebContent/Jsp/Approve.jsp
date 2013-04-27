<%@page import="DBConnection.DBConnection"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
	String user_id = request.getParameter("user_id");

	try
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DBConnection.getConnection();
		String sql = "update police set status = \"Approved\" where user_id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, Integer.parseInt(user_id));
		ps.executeUpdate();
		
		response.sendRedirect("ViewAccountRequest.jsp");
		
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}

%>

</body>
</html>