

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
 * Servlet implementation class UserSetting
 */
@WebServlet("/UserSetting")
public class UserSetting extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserSetting() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession(false);
		
		if(!request.getParameter("passwd").equals(request.getParameter("re_passwd")))
		{
			JOptionPane.showMessageDialog(null, "Passwords does not matches!");
			response.sendRedirect("./Jsp/UserSetting.jsp");
		}		
		else
		{
			try 
			{
				Class.forName("com.mysql.jdbc.Driver");
				
				
				int user_id = Integer.parseInt(session.getAttribute("user_id")+"");
				Connection con = null;
				String sql=null;
				PreparedStatement ps=null;
				ResultSet rs=null;
				
				con = DBConnection.getConnection();
				
				sql = "select user_id from login where user_id=? and passwd=?";
				ps = con.prepareStatement(sql);
				ps.setInt(1, user_id);
				ps.setString(2, request.getParameter("old_passwd").hashCode()+"");
				rs = ps.executeQuery();
				ps.clearParameters();
				
				if(rs.next())
				{
					sql = "update login set passwd=? where user_id = ?";
					ps = con.prepareStatement(sql);
					ps.setString(1, request.getParameter("passwd").hashCode()+"");
					ps.setInt(2, user_id);
					
					int flag = ps.executeUpdate();
					
					if(flag == 1)
					{
						JOptionPane.showMessageDialog(null, "Password changed successfully...");
						response.sendRedirect("./Jsp/UserWelcome.jsp");
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Invalid old password!");
					response.sendRedirect("./Jsp/UserSetting.jsp");
				}
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
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
