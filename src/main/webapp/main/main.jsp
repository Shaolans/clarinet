<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="map_geolocalisation.*"%>

<!DOCTYPE html>
<html>
  <head>
    <meta charset=utf-8>
    <title>Main</title>
    <script src="https://openlayers.org/en/v4.4.2/build/ol.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>    
    <script src="main.js"></script>
    <link rel="stylesheet" type="text/css" href="main.css">
  </head>
  <body>
  	<div id="map"></div>
    <script type="text/javascript">
    	<%String lonlat = NominatimConnection.getLonLat("France");%>
    	var map = new ol.Map({
	        layers: [
	          new ol.layer.Tile({
	            source: new ol.source.OSM()
	          })
	        ],
	        target: 'map',
	        view: new ol.View({
	          center: ol.proj.fromLonLat([<%=lonlat%>]),
	          zoom: 6
	        })
	      });
   	</script>
   	<!--
   	Adresse <br>
   	<input id="id_ad" type="text" name="address">
   	<Button onclick="search(map)">Recherche</Button>-->
   	<h2 style="margin-left:10px">Evenements</h2>
	<div id="search_box">
	<input type="text" id="myInput" onkeyup="myFunction()" placeholder="Rechercher un évènement..." title="Type in a name">
	<button id="searchbtn">Rechercher</button>
	<ul id="myUL">
	  <li><a href="#">Adele</a></li>
	  <li><a href="#">Agnes</a></li>
	
	  <li><a href="#">Billy</a></li>
	  <li><a href="#">Bob</a></li>
	
	  <li><a href="#">Calvin</a></li>
	  <li><a href="#">Christina</a></li>
	  <li><a href="#">Cindy</a></li>
	  <li><a href="#">Adele</a></li>
	  <li><a href="#">Agnes</a></li>
	
	  <li><a href="#">Billy</a></li>
	  <li><a href="#">Bob</a></li>

	</ul>
	</div>
	<script>
	function myFunction() {
	    var input, filter, ul, li, a, i;
	    input = document.getElementById("myInput");
	    filter = input.value.toUpperCase();
	    ul = document.getElementById("myUL");
	    li = ul.getElementsByTagName("li");
	    for (i = 0; i < li.length; i++) {
	        a = li[i].getElementsByTagName("a")[0];
	        if (a.innerHTML.toUpperCase().indexOf(filter) > -1) {
	            li[i].style.display = "";
	        } else {
	            li[i].style.display = "none";
	        }
	    }
	}
	</script>
  </body>
</html>
