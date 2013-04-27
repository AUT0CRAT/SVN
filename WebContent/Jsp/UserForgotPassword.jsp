<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@include file="TitleSlider.jsp"%>
    
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
			
		if(document.Forgot.loginName.value == "")
		{
				alert("Login name cannot be empty!");
				return false;
		}
				
		if(document.Forgot.sec_ans.value == "")
		{
				alert("Security answer field cannot be empty!");
				return false;
		}
	
		return true;
	}
	
	function cancel()
	{
		location.href="UserLogin.jsp";
	}
	
</script>

</head>
<body>

<form action="../UserForgotPassword" name="Forgot" method="post">

<table class="CAT">
<tr><td>Enter the login name:</td> <td><input type="text" name="loginName"></td></tr>
<tr>
<td>Select the security question:</td>
<td>
<select name = "sec_que">
<option value= "What was your first mobile number?"> What was your first mobile number?</option>
<option value= "What was the name of your first pet?"> What was the name of your first pet?</option>
<option value= "What was the name of yur first teacher?"> What was the name of your first teacher?</option>
<option value= "Which sport you like the most?"> Which sport you like the most?</option>
</select>
</td>
</tr>
<tr><td>Enter security answer:</td> <td><input type="text" name="sec_ans"></td></tr>

<tr><td></td><td><input type="submit" value="Submit" onclick="return checkEmptyFields();"> <input type="reset" value="Clear"></td></tr>
<tr><td></td><td><input type="button" value="Cancel" onclick="cancel();"></td></tr>

</table>

</form>
</body>
</html>
