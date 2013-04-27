

import java.io.IOException;
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
 * Servlet implementation class UserForgotPassword
 */
@WebServlet("/UserForgotPassword")
public class UserForgotPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserForgotPassword() 
    {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		RegisterParam.loginName = request.getParameter("loginName");
		RegisterParam.sec_que = request.getParameter("sec_que");
		RegisterParam.sec_ans = request.getParameter("sec_ans");
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DBConnection.getConnection();
			
			PreparedStatement ps = con.prepareStatement("select login.user_id , police.user_name from login,police where login.user_id = police.user_id and login.login_name = ? and login.sec_que=? and login.sec_ans=?");
			ps.setString(1, RegisterParam.loginName);
			ps.setString(2, RegisterParam.sec_que);
			ps.setString(3, RegisterParam.sec_ans.hashCode()+"");
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
			{
				HttpSession session = request.getSession(true);
				session.setAttribute("user_id", rs.getInt(1));
				session.setAttribute("user_name", rs.getString(2));
				response.sendRedirect("./Jsp/UserChangePassword.jsp");
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Invalid login name or password!");
				response.sendRedirect("./Jsp/UserForgotPassword.jsp");
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

