<%@page import="DBConnection.DBConnection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*"%>
    
<%@include file="TitleSlider.jsp"%>    
<%@include file="AdminValidate.jsp"%>
<%
	if(session.getAttribute("admin") == null)
		response.sendRedirect("AdminLogin.jsp");
%>    

    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="../Css/bootstrap.css" type="text/css" rel="stylesheet" media="screen,projection" />
<link href="../Css/Style.css" type="text/css" rel="stylesheet" media="screen,projection" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<%

	try
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DBConnection.getConnection();
		String sql = "select user_id , user_name from police where status = \"Not Approved\"";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		if(rs.next())
		{
			%>
			<table class="CAT" border="2">
			<tr><th>Name</th></tr>
			<%
			rs.previous();
			while(rs.next())
			{
				%>
					<tr><td><%=rs.getString(2)%></td><td><a href="Approve.jsp?user_id=<%=rs.getInt(1)%>">Approve</a></td></tr>
				<%
			}
			%>
			 </table>
			<%
		}
		else
		{
			%>
			<table class="CAT">
			<tr><td>No request pending...</td></tr>
			</table>
			<%
		}
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}

	
%>

<table class="LAT">
<%@include file="AdminMenu.jsp"%>
</table>


</body>
</html>
