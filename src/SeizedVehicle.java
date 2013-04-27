

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

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Sender;
import com.nwc.gcm.S;

/**
 * Servlet implementation class SeizedVehicle
 */
@WebServlet("/SeizedVehicle")
public class SeizedVehicle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SeizedVehicle() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		String loginName = request.getParameter("UserID");
		String vehicleNumber = request.getParameter("vehicle_registration");
		String image = request.getParameter("image");
		
		
		System.out.println(loginName);
		System.out.println(vehicleNumber);
				
		try 
		{
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DBConnection.getConnection();
			String sql=null;
			PreparedStatement ps =null;
			ResultSet rs=null;
			int user_id = -1;
			
			//Getting user id of police from login table
			sql = "select user_id from login where login_name=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, loginName);
			rs = ps.executeQuery();
			
			if(rs.next())
			{
				user_id = rs.getInt(1);
				System.out.println("User Id"+user_id);
			}
			
			if(user_id != -1)
			{
				java.util.Date date = new java.util.Date();
				java.sql.Date sqlDate = new java.sql.Date( date.getTime() );
				
				ps.clearParameters();
				//Inserting information of seized vehicle into seized vehicle table
				sql = "insert into seized_vehicle values(?,?,?,?)";
				ps = con.prepareStatement(sql);
				ps.setString(1,vehicleNumber);
				ps.setInt(2, user_id);
				ps.setDate(3, sqlDate);
				ps.setString(4, image);
				ps.executeUpdate();
				out.print(1);
				System.out.println("Sending Notification");
				sendNotification(vehicleNumber);
				System.out.println("Notification sent");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			out.print(0);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	private static void sendNotification(String vehiclenumber)
	{
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("inside notification");
			String userName=null;
			String userIdQuery = "select user_id,user_name from user where vehicle_no = ?";
			Connection con = DBConnection.getConnection();
			PreparedStatement ps;
			ResultSet rs=null;
			int user_id=-1;
			ps = con.prepareStatement(userIdQuery);
			ps.setString(1, vehiclenumber);
			rs =ps.executeQuery();
			
			if(rs.next())
			{
				user_id = rs.getInt(1);
				userName = rs.getString(2);
			}
			System.out.println("User Id of user whose vehicle has been seized:"+user_id);
			
			//To get login name for sending notification to user
			ps.clearParameters();
			userIdQuery = "select login_name from login where user_id = ?";
			ps = con.prepareStatement(userIdQuery);
			ps.setInt(1, user_id);
			rs = ps.executeQuery();
			
			String loginName =null;
			
			if(rs.next())
			{
				loginName = rs.getString(1);
			}
			
			ps.clearParameters();
			userIdQuery = "select regID from gcm_users where user_id = ?";
			ps = con.prepareStatement(userIdQuery);
			ps.setInt(1, user_id);
			rs = ps.executeQuery();
			
			String regid=null;
			
			if(rs.next())
			{
				regid = rs.getString(1);
				System.out.println("User device registration id:"+regid);
				Message message = new Message.Builder().addData(S.Parameter.MESSAGE_VEHICLE_KEY,vehiclenumber).addData("UserID",userName).addData("LoginID", loginName).build();
				Sender sender = getSender();
				sender.send(message,regid , 5);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	protected static Sender getSender() {
    	return new Sender(S.API_KEY);
	}

}
