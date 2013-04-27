import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import DBConnection.DBConnection;

/**
 * Servlet implementation class UserLogin
 */
@WebServlet("/UserLogin")
public class UserLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
//		PrintWriter out = response.getWriter();
//				
//		out.print(request.getParameter("loginName"));
//		out.print(request.getParameter("passwd"));
		
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DBConnection.getConnection();
			String sql="select user_id from login where login_name=? and passwd = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, request.getParameter("loginName"));
			ps.setString(2, request.getParameter("passwd").hashCode()+"");
			ResultSet rs = ps.executeQuery();
			ps.clearParameters();
			
			if(rs.next())
			{
				HttpSession session = request.getSession(true);
				session.setAttribute(Session.UserId,rs.getInt(1));
				
				sql = "select police.status,police.user_name,login.login_name from police,login where login.user_id = police.user_id and login.user_id = ?";
				ps = con.prepareStatement(sql);
				ps.setInt(1, Integer.parseInt(rs.getString(1)));
				rs = ps.executeQuery();
				
				if(rs.next())
				{
					if(rs.getString(1).equals("Not Approved"))
					{
						JOptionPane.showMessageDialog(null, "Account is not authorised!");
						response.sendRedirect("./Jsp/UserLogin.jsp");
					}
					
					else
					{
						session.setAttribute(Session.LoginName,rs.getString(3));
						session.setAttribute(Session.UserName,rs.getString(2));
						response.sendRedirect("./Jsp/UserWelcome.jsp");
					}
				}
	
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Invalid user name or password!");
				response.sendRedirect("./Jsp/UserLogin.jsp");
				
			}
			
		} 
		catch (ClassNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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