<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="DBConnection.DBConnection"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="TitleSlider.jsp"%>
<%@include file="Validate.jsp"%>
<%@include file="UserGreet.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="../Css/bootstrap.css" type="text/css" rel="stylesheet"
	media="screen,projection" />
<link href="../Css/Style.css" type="text/css" rel="stylesheet"
	media="screen,projection" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script type="text/javascript">
	function checkEmptyFields() {
		if (document.UserAccount.userName.value == "") {
			alert("User name cannot be empty!");
			return false;
		}
		if (document.UserAccount.loginName.value == "") {
			alert("Login name cannot be empty!");
			return false;
		}
		return true;
	}

	function cancel() {
		location.href = "UserWelcome.jsp";
	}

	function redirect() {
		var res = confirm("Do you want to continue?");

		if (res == true) {
			location.href = "UserDeleteAccount.jsp";
		}
	}
</script>

</head>
<body>
	<form action="/SVN/UserAccount" name="UserAccount" method="post">
		<table class="LAT">
			<%@include file="UserMenu.jsp"%>
		</table>
		<table class="CAT">
			<%
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DBConnection.getConnection();
				String sql = null;
				PreparedStatement ps = null;
				ResultSet rs = null;
				sql = "select police.user_name ,login.login_name from login,police where login.login_name = ?";
				ps = con.prepareStatement(sql);
				ps.setString(1, session.getAttribute("login_name") + "");
				rs = ps.executeQuery();

				if (rs.next()) {
			%>
			<tr>
				<td>User Name:</td>
				<td><input type="text" name="userName"
					value="<%=rs.getString(1)%>"></td>
			</tr>
			<tr>
				<td>Login Name:</td>
				<td><input type="text" name="loginName"
					value="<%=rs.getString(2)%>"></td>
			</tr>
			<tr>
				<td>Select the security question:</td>
				<td><select name="sec_que">
						<option value="What was your first mobile number?">What
							was your first mobile number?</option>
						<option value="What was the name of your first pet?">
							What was the name of your first pet?</option>
						<option value="What was the name of yur first teacher?">
							What was the name of your first teacher?</option>
						<option value="Which sport you like the most?">Which
							sport you like the most?</option>
				</select></td>
			</tr>
			<tr>
				<td>Security Answer:</td>
				<td><input type="text" name="sec_ans"></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="update"
					onclick="return checkEmptyFields()"> <input type="button"
					value="cancel" onclick="cancel();"></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="button" value="Delete Account"
					onclick="redirect();"></td>
			</tr>
			<%
				}
			%>
		</table>
	</form>
</body>
</html>