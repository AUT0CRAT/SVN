<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="TitleSlider.jsp"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="../Css/bootstrap.css" type="text/css" rel="stylesheet" media="screen,projection" />
<link href="../Css/Style.css" type="text/css" rel="stylesheet" media="screen,projection" /><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="../AdminLogin" method="POST" name="LoginAdmin" method="post">

<table class="CAT">
<tr></tr>
<tr></tr><tr></tr><tr></tr>
<tr><td>Login name:</td> <td><input type="text" name="loginName"></td></tr>
<tr><td>Password:</td> <td><input type="Password" name="passwd"></td></tr>
<tr><td></td><td><input type="submit" value="Login" onclick="return checkEmptyFields();"> <input type="reset" value="Clear"></td></tr>
</table>

</form>

</body>
</html>