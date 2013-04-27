<!DOCTYPE html>
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

	var map;
	var markersArray = [];

	function initialize() 
	{
		var myLatLng = new google.maps.LatLng(18.520185,73.842237);
		
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
	google.maps.event.addDomListener(window, 'load', initialize);
	
	function addMarker(location) {
		  marker = new google.maps.Marker({
		    position: location,
		    map: map
		  });
		  markersArray.push(marker);
	}
	
	function load()
	{	
		addMarker(new google.maps.LatLng(18.513287,73.856231));
		addMarker(new google.maps.LatLng(18.520266,73.842452));
		addMarker(new google.maps.LatLng(18.525637,73.849189));
		addMarker(new google.maps.LatLng(18.51758,73.84385));
		addMarker(new google.maps.LatLng(18.524071,73.841103));
	} 
	
	function cancel()
	{
		location.href="UserLogin.jsp";
	}
 	
</script>
</head>
<table class="LAT"><tr><td><img alt="" src="/SVN/Image/back.jpeg"  height = "50" width = "150" onclick="cancel();"></img></td></tr></table>
<body onload="load()">
<div id="map-canvas"></div>
</body>

</html>