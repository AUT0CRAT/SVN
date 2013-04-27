import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DBConnection.DBConnection;

/**
 * Servlet implementation class AppLogin
 */
@WebServlet("/AppLogin")
public class AppLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AppLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("accessed AppLogin");
		PrintWriter out = response.getWriter();
		
		ParamClass.userID = request.getParameter("UserID");
		ParamClass.password = request.getParameter("password");
		ParamClass.type = request.getParameter("type");
		
		/*AppParam.userID = AppParam.userID.trim();
		AppParam.password = AppParam.password.trim();
		AppParam.type = AppParam.type.trim();
		*/
		
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DBConnection.getConnection();
			
			String sql="";
			ResultSet rs = null;
			PreparedStatement ps=null;
			
			if(ParamClass.type.equalsIgnoreCase("police"))
			{
				/**
				 * Police authentication
				 */
				
				sql="select login.user_id from login,police where login.login_name = ? and login.passwd = ? and login.type=? and police.status=?";
				ps = con.prepareStatement(sql);
				ps.setString(1, ParamClass.userID);
				ps.setString(2, ParamClass.password);	//password is already in hash code format
				ps.setString(3, ParamClass.type);
				ps.setString(4, "Approved");
				rs = ps.executeQuery();
				ps.clearParameters();
				
				System.out.println("Login name: "+ParamClass.userID);
				/*System.out.println("Password: "+ParamClass.password);
				System.out.println("Type: "+ParamClass.type);*/	
				
			}
			else
			{
				/**
				 * Regular user authentication
				 */
				sql = "select user_id from login where login.login_name = ? and passwd = ?";
				ps = con.prepareStatement(sql);
				ps.setString(1, ParamClass.userID);
				ps.setString(2, ParamClass.password);
				rs = ps.executeQuery();
				ps.clearParameters();
				
				System.out.println("Login Name: "+ParamClass.userID);
			}
			
			if(rs.next())
			{
				//Authorised
				out.print(1);
				System.out.println("Login successfull");
			}
			else
			{
				//Account not authorised..
				out.print(0);
				System.out.println("Login not successfull");
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
