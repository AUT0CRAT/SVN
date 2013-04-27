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

/**
 * Servlet implementation class UserAccount
 */
@WebServlet("/UserAccount")
public class UserAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserAccount() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession(false);
		String sql = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con=null;
		
		if(request.getParameter("loginName").equals(session.getAttribute(Session.LoginName)) == false)
		{
			try {
				
				Class.forName("com.mysql.jdbc.Driver");
				con = DBConnection.DBConnection.getConnection();
				//To check whether login name already exists
				sql = "select user_id from login where login_name=?";
				ps = con.prepareStatement(sql);
				ps.setString(1, request.getParameter("loginName"));
				rs = ps.executeQuery();
				ps.clearParameters();
				if(rs.next())
				{
					JOptionPane.showMessageDialog(null, "Login name already exists!");
					response.sendRedirect("./Jsp/UserAccount.jsp");
				}
				else
				{
					//Updating user table to new user name  
					sql = "update police set user_name = ? where user_id = ?";
					ps = con.prepareStatement(sql);
					ps.setString(1, request.getParameter("userName"));
					ps.setInt(2, Integer.parseInt(session.getAttribute(Session.UserId)+""));
					ps.executeUpdate();
					
					ps.clearParameters();
					
					//Updating login table to new login name
					sql = "update login set login_name = ? where user_id = ?";
					ps = con.prepareStatement(sql);
					ps.setString(1, request.getParameter("loginName"));
					ps.setInt(2, Integer.parseInt(session.getAttribute(Session.UserId)+""));
					ps.executeUpdate();
					
					ps.clearParameters();
					
					if(request.getParameter("sec_ans")!=null)
					{
						sql = "update login set sec_que= ? , sec_ans =? where user_id = ?";
						ps = con.prepareStatement(sql);
						ps.setString(1, request.getParameter("sec_que"));
						ps.setString(2, request.getParameter("sec_ans"));
						ps.setInt(3, Integer.parseInt(session.getAttribute(Session.UserId)+""));
						ps.executeUpdate();
					}
					
					session.setAttribute(Session.LoginName, request.getParameter("loginName"));
					session.setAttribute(Session.UserName, request.getParameter("userName"));
					response.sendRedirect("./Jsp/UserAccount.jsp");
				}
				
				
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		else
		{
			if(request.getParameter("sec_ans")!=null)
			{
				try
				{
					con = DBConnection.DBConnection.getConnection();
					sql = "update login set sec_que= ? , sec_ans =? where user_id = ?";
					ps = con.prepareStatement(sql);
					ps.setString(1, request.getParameter("sec_que"));
					ps.setString(2, request.getParameter("sec_ans"));
					ps.setInt(3, Integer.parseInt(session.getAttribute(Session.UserId)+""));
					ps.executeUpdate();
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			response.sendRedirect("./Jsp/UserAccount.jsp");
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
