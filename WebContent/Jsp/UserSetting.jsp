<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="TitleSlider.jsp" %>
<%@include file="Validate.jsp" %>    

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
			
		if(document.Change.passwd.value == "")
		{
				alert("Password cannot be empty!");
				return false;
		}
				
		if(document.Change.re_passwd.value == "")
		{
				alert("Re-Enter password cannot be empty!");
				return false;
		}
	
		return true;
	}
	
	function cancel()
	{
		location.href="UserWelcome.jsp";
	}
	
</script>

</head>
<body>

<form action="../UserSetting" method="post" name="Change">

<%@include file="UserGreet.jsp" %>


<table class="LAT">
<tr><td><%@include file="UserMenu.jsp"%></td></tr>
</table>

<table class="CAT">
<tr><td>Enter the old password:</td> <td><input type="password" name="old_passwd"></td></tr>
<tr><td>Enter the new password:</td> <td><input type="password" name="passwd"></td></tr>
<tr><td>Re-Enter the new password:</td> <td><input type="password" name="re_passwd"></td></tr>
<tr><td></td><td><input type="submit" value="Submit" onclick="return checkEmptyFields();"> <input type="reset" value="Clear"></td></tr>
<tr><td></td><td><input type="button" value="Cancel" onclick="cancel();"></td></tr>
</table>
</form>

</body>
</html>