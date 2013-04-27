

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

/**
 * Servlet implementation class AdminSetting
 */
@WebServlet("/AdminSetting")
public class AdminSetting extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminSetting() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String passwd1 = request.getParameter("passwd1");
		String passwd2 = request.getParameter("passwd2");
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			String sql=null;
			PreparedStatement ps=null;
			ResultSet rs=null;
			Connection con = DBConnection.DBConnection.getConnection();
			
			sql = "select loginName from admin where passwd = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, passwd1.hashCode()+"");
			rs = ps.executeQuery();
			
			if(rs.next())
			{
				sql = "update admin set passwd = ?";
				ps = con.prepareStatement(sql);
				ps.setString(1, passwd2.hashCode()+"");
				ps.executeUpdate();
				
				JOptionPane.showMessageDialog(null, "Password changed successfully...");
				response.sendRedirect("./Jsp/AdminWelcome.jsp");
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Invalid old password!");
				response.sendRedirect("./Jsp/AdminSetting.jsp");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
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
