import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import DBConnection.DBConnection;

/**
 * Servlet implementation class UserRegister
 */
@WebServlet("/UserRegister")
public class UserRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserRegister() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		RequestDispatcher desp=null;

		/*if(!request.getParameter("passwd").equals(request.getParameter("re_passwd")))
		{
			JOptionPane.showMessageDialog(null, "Password does not matches!");
			desp = getServletContext().getRequestDispatcher("/Jsp/UserRegister.jsp");
			desp.forward(request, response);
		}*/

		/*else
		{*/
			try 
			{
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DBConnection.getConnection();

				RegisterParam.userName = request.getParameter("userName");
				RegisterParam.loginName = request.getParameter("loginName");
				RegisterParam.passwd = request.getParameter("passwd");
				RegisterParam.sec_que = request.getParameter("sec_que"); 
				RegisterParam.sec_ans = request.getParameter("sec_ans");
				RegisterParam.policeStationId = request.getParameter("policeStationId");
				RegisterParam.userType="police";

				PreparedStatement ps=null;
				ResultSet rs = null;

				ps = con.prepareStatement("select login_name from login where login_name = ?");
				ps.setString(1, RegisterParam.loginName);
				rs = ps.executeQuery();

				ps.clearParameters();

				if(rs.next())
				{
					JOptionPane.showMessageDialog(null, "Login name already exists!");
					desp = getServletContext().getRequestDispatcher("/Jsp/UserRegister.jsp");
					desp.forward(request, response);
				}
				else
				{
					int login_id=-1;
					//Getting new login id
					ps = con.prepareStatement("select max(user_id) from login");
					rs = ps.executeQuery();

					ps.clearParameters();

					if(rs.next())
					{
						login_id = rs.getInt(1) + 1; 
					}
					else
					{
						login_id = 1;
					}
					//End
					
					//Inserting record into login table
					String sql=null;
					sql = "insert into login values(?,?,?,?,?,?)";
					ps = con.prepareStatement(sql);

					ps.setInt(1, login_id);
					ps.setString(2, RegisterParam.loginName);
					ps.setString(3, RegisterParam.passwd.hashCode()+"");
					ps.setString(4, RegisterParam.userType);
					ps.setString(5, RegisterParam.sec_que);
					ps.setString(6, RegisterParam.sec_ans.hashCode()+"");

					ps.executeUpdate();

					ps.clearParameters();
					//Inserting records into police table
					sql = "insert into police values(?,?,?,?)";
					ps = con.prepareStatement(sql);

					ps.setInt(1,login_id);
					ps.setString(2,RegisterParam.userName);
					ps.setInt(3,Integer.parseInt(RegisterParam.policeStationId));
					ps.setString(4,"Not Approved");

					ps.executeUpdate();
					ps.clearParameters();

					JOptionPane.showMessageDialog(null, "Account created successfully..");
					response.sendRedirect("./Jsp/UserLogin.jsp");
				}
			} 
			catch (ClassNotFoundException e) 
			{
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}