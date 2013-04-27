

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

import org.json.simple.JSONObject;

/**
 * Servlet implementation class VehiclePosition
 */
@WebServlet("/VehiclePosition")
public class VehiclePosition extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VehiclePosition() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			String sql = null;
			Connection con = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			
			con = DBConnection.DBConnection.getConnection();
			
			sql = "select vehicle_no from login,user where login.user_id = user.user_id and login.login_name=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, request.getParameter("UserID"));	//Login Name of user
			rs = ps.executeQuery();
			
			if(rs.next())
			{
				String vehicleNumber;
				
				vehicleNumber = rs.getString(1);
				
				sql = "select user_id from seized_vehicle where vehicle_number=?";
				ps = con.prepareStatement(sql);
				ps.setString(1, vehicleNumber);
				
				rs = ps.executeQuery();
				
				if(rs.next())
				{
					int user_id = rs.getInt(1);
					
					sql = "select station_id from police where user_id=?";
					ps = con.prepareStatement(sql);
					ps.setInt(1, user_id);
					rs = ps.executeQuery();
					
					if(rs.next())
					{
						int station_id = rs.getInt(1);
						
						sql = "select lat,lng from police_station where station_id =?";
						ps = con.prepareStatement(sql);
						ps.setInt(1, station_id);
						rs = ps.executeQuery();
						
						if(rs.next())
						{
							
							double lat = rs.getDouble(1);
							double lng = rs.getDouble(2);

							JSONObject jsonObj = new JSONObject();
							jsonObj.put("Lat", lat);
							jsonObj.put("Lng", lng);
							out.print(jsonObj);
						}
					}
				}
			}
			
		} 
		catch (Exception e) 
		{
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

}
