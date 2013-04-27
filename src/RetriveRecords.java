

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.nwc.gcm.S;

import DBConnection.DBConnection;

/**
 * Servlet implementation class RetriveRecords
 */
@WebServlet("/RetriveRecords")
public class RetriveRecords extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RetriveRecords() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String loginName = request.getParameter("UserID");
		PrintWriter out = response.getWriter();
		ResultSet rs = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DBConnection.getConnection();
			
			String query = "select vehicle_no,date from seized_vehicle sv,login l where l.user_id = se.user_id and l.login_name = ?";
			java.sql.PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, loginName);
			rs = ps.executeQuery();
			
			JSONArray array = new JSONArray();
			
			while(rs.next())
			{
				JSONObject obj = new JSONObject();
				String vehicleno = rs.getString(1);
				obj.put("vehicle_number", vehicleno);
				obj.put("date", rs.getString(2));
				array.add(obj);
			}
			JSONObject jobj = new JSONObject();
			jobj.put("vehicles", array);
			
			out.print(jobj);
			
		}
		catch (ClassNotFoundException e)
		{
			out.print(0);
			e.printStackTrace();
		} catch (SQLException e) {
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
