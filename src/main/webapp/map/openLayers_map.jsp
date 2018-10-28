<!DOCTYPE html>
<html>
  <head>
    <meta charset=utf-8>
    <title>Open Layers Map</title>
    <script src="https://openlayers.org/en/v4.4.2/build/ol.js"></script>
    <style type="text/css">
      #map {
        height: 300px;
        width: 300px;
      }
    </style>
  </head>
  <body>
  	<div id="address">Faubourg de la péchoire, Saint-didier en velay</div>
  	<button id='btn'>TEST</button>
  	<script type="text/javascript">
  		document.getElementById('btn').onclick = test
  		function reload_js(src) {
  	        $('script[src="' + src + '"]').remove();
  	        $('<script>').attr('src', src).appendTo('head');
  	    }
  		function test(){
			document.getElementById('address').innerHTML = '4 place jussieu, 75005 Paris, France';
	  	    reload_js('map.js');
  		}
  	</script>
  	<div id="map"></div>
    <script type="text/javascript" src="map.js"></script>
  </body>
</html>
