<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<%@include file="TitleSlider.jsp"%>
<%@include file="AdminValidate.jsp"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="../Css/bootstrap.css" type="text/css" rel="stylesheet" media="screen,projection" />
<link href="../Css/Style.css" type="text/css" rel="stylesheet" media="screen,projection" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<table class="CAT">
<tr><td><h2>Welcome <%=session.getAttribute("admin")%></h2></td></tr>
</table>

<table class="LAT">
<tr><td><a href="ViewAccountRequest.jsp">View Request</a></td></tr>
<tr><td><a href="AdminSetting.jsp">Settings</a></td></tr>
<tr><td><a href="Logout.jsp">Logout</a></td></tr>
</table>

</body>
</html>