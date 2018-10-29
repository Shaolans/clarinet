<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="map_geolocalisation.*"%>

<!DOCTYPE html>
<html>
  <head>
    <meta charset=utf-8>
    <title>Clarinet</title>
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
   	<h2 style="margin-left:10px">Clarinet</h2>
	<div id="search_box">
	<!-- <input type="text" id="myInput" onkeyup="myFunction()" placeholder="Rechercher un évènement..." title="Type in a name"> -->
	<input type="text" id="myInput" onkeyup="enterInput()" placeholder="Rechercher un évènement..." title="Barre de recherche">
	<button id="searchbtn" onclick="loadList()">Rechercher</button>
	<ul id="myUL">
	</ul>
	<a id="prev" class="arrow arrow-left" title="Previous" onclick="prevLoad()"></a>
	<a id="next" class="arrow arrow-right" title="Next" onclick="nextLoad()"></a>
	</div>
  </body>
</html>
