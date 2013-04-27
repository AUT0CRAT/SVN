
<%
	if(session.getAttribute("user_id") == null)
		response.sendRedirect("UserLogin.jsp");
%>