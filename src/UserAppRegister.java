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

import DBConnection.DBConnection;

/**
 * Servlet implementation class UserAppRegister
 */
@WebServlet("/UserAppRegister")
public class UserAppRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserAppRegister() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		PrintWriter out = response.getWriter();
		String UserName=null,LoginName=null,Password=null,VehicleNumber=null,SecQue=null,SecAns=null,EmailId=null,MobileNumber=null,
				Address=null,Type=null;
		UserName = request.getParameter(User.UserName);		
		LoginName = request.getParameter(User.LoginName);
		Password = request.getParameter(User.Password);
		VehicleNumber = request.getParameter(User.VehicleNumber);
		SecQue = request.getParameter(User.SecQue);
		SecAns = request.getParameter(User.SecAns);
		EmailId = request.getParameter(User.EmailId);
		MobileNumber = request.getParameter(User.MobileNumber);
		Address = request.getParameter(User.Address);
		Type = request.getParameter(User.TYPE);
		
		/*System.out.println("UserName:"+User.UserName);
		System.out.println("LoginName:"+User.LoginName);
		System.out.println("Password:"+User.Password);
		System.out.println("Vehicle Number:"+User.VehicleNumber);
		System.out.println("SecQue:"+User.SecQue);
		System.out.println("SecAns:"+User.SecAns);
		System.out.println("Email Id:"+User.EmailId);
		System.out.println("Mobile Number:"+User.MobileNumber);
		System.out.println("Address:"+User.Address);
		System.out.println("Type:"+User.TYPE);*/
		
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DBConnection.getConnection();
			PreparedStatement ps = null;
			ResultSet rs = null;
			String sql = null;
			
			
			sql = " select user_id from login where login_name = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, LoginName);
			rs = ps.executeQuery();
			
			if(rs.next())
			{
				out.print(3);		//Login name exists. Sending status 3 to user register app
				System.out.println(3);
			}
			else
			{
				ps.clearParameters();
				sql = "select user_id from user where vehicle_no = ?";
				ps = con.prepareStatement(sql);
				ps.setString(1, VehicleNumber);
				rs = ps.executeQuery();
				
				if(rs.next())
				{
					out.print(4);	//Vehicle number already registered. Sending status 4 to user register app
					System.out.println(4);
				}
				else
				{
					int login_id=-1;
					ps.clearParameters();
					//To select new login id for user
					sql = "select max(user_id) from login";
					ps = con.prepareStatement(sql);
					rs = ps.executeQuery();
					
					if(rs.next())
					{
						login_id = rs.getInt(1)+1;
					}
					else
					{
						login_id = 1;
					}
					
					ps.clearParameters();
					
					//Inserting new values into login table
					sql = "insert into login values(?,?,?,?,?,?)";
					ps = con.prepareStatement(sql);
					ps.setInt(1, login_id);
					ps.setString(2, LoginName);
					ps.setString(3, Password.hashCode()+"");
					ps.setString(4, Type);
					ps.setString(5, SecQue);
					ps.setString(6, SecAns.hashCode()+"");
					ps.executeUpdate();
					
					ps.clearParameters();
					//Inserting values into user table
					sql = "insert into user values(?,?,?,?,?,?)";
					ps = con.prepareStatement(sql);
					ps.setInt(1, login_id);
					ps.setString(2, UserName);
					ps.setString(3, EmailId);
					ps.setString(4, MobileNumber);
					ps.setString(5, VehicleNumber);
					ps.setString(6, Address);
					ps.executeUpdate();
					
					ps.clearParameters();
					
					out.print(1);
					System.out.println(1);
				}
			}
			
		} 
		catch (Exception e) 
		{
			// TODO: handle exception
			out.print(0);
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