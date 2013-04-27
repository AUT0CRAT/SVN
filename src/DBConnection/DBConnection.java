package DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection
{
	public static Connection getConnection()
	{
		Connection con = null;
		try 
		{
			con = DriverManager.getConnection("jdbc:mysql://localhost/svn","root","sumit30");
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return con;
	}
}