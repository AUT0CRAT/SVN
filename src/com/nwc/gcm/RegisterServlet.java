package com.nwc.gcm;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nwc.gcm.S.Parameter;

/**
 * Servlet implementation class RegisterServlet
 */

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	//private static final String LOGIN_NAME = "login_name";
	
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String regId = request.getParameter(Parameter.PARAMETER_REG_ID);
		String loginName = request.getParameter("UserID");
//		String loginName = request.getParameter(LOGIN_NAME);
		System.out.println("Registering request "+regId);
		boolean flag = Data.register(regId,loginName);
		PrintWriter pw = response.getWriter();
		if(flag)
			pw.print(S.Status.SUCCESS);
		else
			pw.write(S.Status.FAIL);
		pw.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);		
	}

}
