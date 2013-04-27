package com.nwc.gcm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DBConnection.DBConnection;


public final class Data {
	
	//private static final ArrayList<String> registeredIds = new ArrayList<String>();
	
	/**
	 * Since Creating object of Data Should NOt be allowed
	 */
	private Data()
	{
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Register a Device
	 * @param regId The id to be registered for notification 
	 */
	
	public static boolean register(String regId, String loginName)
	{
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		
			Connection con = DBConnection.getConnection();
			
			String userIdQuery = "select user_id from login where login_name = ?";
			int user_id = 0;
			PreparedStatement ps = null;
			ps = con.prepareStatement(userIdQuery);
			ps.setString(1, loginName);
			ResultSet rs = ps.executeQuery();
			
			rs.first();
			user_id = rs.getInt(1);
			
			System.out.println("UserID"+user_id);
			
			ps.clearParameters();
			String regQuery = "insert into gcm_users(regID,user_id) values(?,?)";
			ps = con.prepareStatement(regQuery);
			ps.setString(1, regId);
			ps.setInt(2, user_id);
			int i = ps.executeUpdate();
			
			
			
			if(i != 1)
			{
				return false;
			}
			
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	/**
	 * Unregister a device
	 * @param regId
	 */
	
	public static void unregister(String regId)
	{
		Connection con = DBConnection.getConnection();
		String unregisterQuery = "delete from gcm_users where regId = ?";
		try
		{
			PreparedStatement s = con.prepareStatement(unregisterQuery);
			s.setString(1, regId);
			s.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
}
