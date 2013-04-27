import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import DBConnection.DBConnection;

/**
 * Servlet implementation class AdminLogin
 */
@WebServlet("/AdminLogin")
public class AdminLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		
		String loginName = request.getParameter("loginName");
		String passwd = request.getParameter("passwd");
		
		response.setContentType("text/html");
		
		out.print("Login Name"+loginName+"<br>");
		out.print("Password:"+passwd);
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");	
			
			Connection con = DBConnection.getConnection(); 

			PreparedStatement ps = con.prepareStatement("select loginName,passwd from admin");
			
			ResultSet rs = ps.executeQuery();
			
			HttpSession session = request.getSession(true);
			
			session.setAttribute("admin",loginName);
			
			if(rs.next())
			{
				if(loginName.matches(rs.getString(1)) == false)
				{
					JOptionPane.showMessageDialog(null, "Invalid Login name or password");
					response.sendRedirect("./Jsp/AdminLogin.jsp");
				}
				else if(passwd.matches(rs.getString(2))== false)
				{
					JOptionPane.showMessageDialog(null, "Invalid Login name or password");
					response.sendRedirect("./Jsp/AdminLogin.jsp");
				}
				response.sendRedirect("./Jsp/AdminWelcome.jsp");
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Not registered");
				response.sendRedirect("./Jsp/AdminLogin.jsp");
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
