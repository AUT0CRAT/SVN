<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@include file="TitleSlider.jsp"%>    

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="../Css/bootstrap.css" type="text/css" rel="stylesheet" media="screen,projection" />
<link href="../Css/Style.css" type="text/css" rel="stylesheet" media="screen,projection" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Seized Vehicle Notifier</title>


<script type="text/javascript">

	function checkEmptyFields()
	{
		if(document.UserLogin.loginName.value == "")
		{
			alert("Login name cannot be empty!");
			return false;
		}
		if(document.UserLogin.passwd.value == "")
		{
				alert("Password cannot be empty!");
				return false;
		}
		return true;
	}
</script>

</head>
<body>

<form action="../UserLogin" method="POST" name="UserLogin">

<table class="RAT">
<tr><td></td><td><h1>Login</h1></td></tr>
<tr><td>Login name:</td> <td><input type="text" name="loginName"></td></tr>
<tr><td>Password:</td> <td><input type="Password" name="passwd"></td></tr>
<tr><td></td><td><input type="submit" value="Login" onclick="return checkEmptyFields();"> <input type="reset" value="Clear"></td></tr>
<tr><td></td><td><a href="UserForgotPassword.jsp">Can't access your account?</a></td></tr>
<tr><td></td><td><a href="UserRegister.jsp">Create an account</a></td></tr>
</table>

<table class="SearchLink">
<tr><td><a href="FindVehicle.jsp">Click here to search your vehicle...</a></td></tr>
<tr><td><a href="ParkingPlaces.jsp">Click here to find out parking places in pune...</a></td></tr>
</table>

</form>
</body>
</html>