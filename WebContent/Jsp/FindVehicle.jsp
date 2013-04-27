<!DOCTYPE html>
<%@page import="javax.swing.JOptionPane"%>
<%@page import="DBConnection.DBConnection"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="sun.font.Script"%>
<html>
<head>
<link href="../Css/Style.css" type="text/css" rel="stylesheet" media="screen,projection" />
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<style type="text/css">
html {
	height: 100%
}

body {
	height: 130%;
	width: 100%;
	margin: 10;
	padding: 0
}

#map-canvas {
	height: 70%
}
</style>

<script type="text/javascript"
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBmtvlul6Kh1Ja_Rp5KgCTYLwiH-MTdoF4&sensor=true">
	
</script>

<script type="text/javascript">

	var map = null;
	
	function initialize(lat,lng) 
	{
		//document.write("In initialize");
		document.write(lat+" "+lng);
		//18.520185,73.842237
		/* var lat = document.Vehicle.lat.value;
		var lng = document.Vehicle.lng.value; */
		
		var myLatLng = new google.maps.LatLng(lat,lng);
		
		var mapOptions = {
			center : myLatLng,
			zoom : 14,
			mapTypeId : google.maps.MapTypeId.ROADMAP
		};
	
		map = new google.maps.Map(document.getElementById("map-canvas"),mapOptions);
	
		
		var marker = new google.maps.Marker({
			position : myLatLng,
		});
		marker.setMap(map);		
	}
	//google.maps.event.addDomListener(window, 'load', initialize);
	
	function showMessage()
	{
		alert("Your vehicle is not being seized..");
	}
	
	function cancel()
	{
		location.href="UserLogin.jsp";
	}
 	
</script>
</head>
<body>
<form name="FindVehicle">
<table class="CAT">
<tr><td>Enter vehicle number:</td><td><input type="number" name="vehicle_no" id = "vehicle_no"> <input type="submit" value="Submit"></td></tr>
</table>
<table class="LAT"><tr><td><img alt="" src="/SVN/Image/back.jpeg"  height = "50" width = "150" onclick="cancel();"></img></td></tr></table>

<%!
	double lat = 0;
	double lng = 0;
%>


<%
	if(request.getParameter("vehicle_no") != null)
	{
		try
		{
			String sql = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			Connection con = null;
			
			Class.forName("com.mysql.jdbc.Driver");
			con = DBConnection.getConnection();
			
			sql = "select user_id from seized_vehicle where vehicle_number=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, request.getParameter("vehicle_no"));
			rs = ps.executeQuery();
			
			ps.clearParameters();
			
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
						lat = rs.getDouble(1);
						lng = rs.getDouble(2);
						//out.print("lat & lng"+rs.getFloat(1)+"   "+rs.getFloat(2));
						%>
							<input type="hidden" name = "lat" value="<%=lat%>">
							<input type="hidden" name = "lng" value="<%=lng%>">

						<%
					}
					else
					{
						%>
						<script type="text/javascript">
							showMessage();
						</script>
					<%
					}
				}
				
			}
			else
			{
				%>
					<script type="text/javascript">
						showMessage();
					</script>
				<%			
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}
%>

</form>

<% 
	if(request.getParameter("vehicle_no") != "" && request.getParameter("vehicle_no") != null)
	{
		if(lat != 0 && lng != 0)
		{
			%>
				<script type="text/javascript">
						initialize(<%=lat%>,<%=lng%>);			
				</script>
				<div id="map-canvas"></div>
			<%
		}
	}
%>
</body>
</html>