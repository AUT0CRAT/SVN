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

<script type="text/javascript">

	function checkEmptyFields()
	{
		if(document.AdminSetting.passwd1.value == "")
		{
			alert("Old password cannot be empty!");
			return false;
		}
		
		if(document.AdminSetting.passwd2.value == "")
		{
				alert("New password cannot be empty!");
				return false;
		}
		
		if(document.AdminSetting.passwd3.value == "")
		{
				alert("Re-Enter password cannot be empty!");
				return false;
		}
		if(document.AdminSetting.passwd2.value.length < 6)
		{
			alert("Password cannot be less than 6 characters!");
			return false;
		}
		if(document.AdminSetting.passwd2.value != document.AdminSetting.passwd3.value)
		{
			alert("Password does not matches!");
			return false;
		}
		return true;
	}
	
	
	function checkPasswordMatch() 
	{
	    var password = $("#txtNewPassword").val();
	    var confirmPassword = $("#txtConfirmPassword").val();

	    if (password != confirmPassword)
	    {
	        $("#divCheckPasswordMatch").html("Passwords do not match!");
	        return false;
	    }
	    else
	    {
	        $("#divCheckPasswordMatch").html("Passwords match.");
	        return true;
	    }
	}
	
	
	function redirect()
	{
		location.href="AdminWelcome.jsp";
	}
	
</script>

</head>
<body>

<form action="/SVN/AdminSetting" name ="AdminSetting" method="post">

<table class="LAT">
<%@include file="AdminMenu.jsp"%>
</table>


<table class="CAT">
<tr><td>Enter the old password:</td> <td><input type="password" name="passwd1"></td></tr>
<tr><td>Enter the new password:</td> <td><input type="password" name="passwd2" id="txtNewPassword"></td></tr>
<tr><td>Confirm new password:</td> <td><input type="password" name="passwd3" id="txtConfirmPassword" onkeyup="return checkPasswordMatch();">
<div id="divCheckPasswordMatch"> </div></td></tr>

<tr><td></td><td><input type="submit" value = "Submit" onclick="return checkEmptyFields();"> <input type="button" value = "Cancel" onclick="redirect();"></td></tr>

</table>
</form>
</body>
</html>