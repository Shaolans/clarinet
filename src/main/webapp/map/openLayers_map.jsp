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
    <div id="map" class="map"></div>
    <script>
      var map = new ol.Map({
        layers: [
          new ol.layer.Tile({
            source: new ol.source.OSM()
          })
        ],
        target: 'map',
        view: new ol.View({
          center: ol.proj.fromLonLat([2.3551612999999634, 48.8464406]),
          zoom: 17
        })
      });
      
      var marker = new ol.Feature({
    	  geometry: new ol.geom.Point(
    	    ol.proj.fromLonLat([2.3551612999999634, 48.8464406])
    	  )
      });
      var iconStyle = new ol.style.Style({
    	  image: new ol.style.Icon(({
    		src: 'marker.png'
    	  }))
      });
      marker.setStyle(iconStyle);
      var vectorSource = new ol.source.Vector({
    	  features: [marker]
      });
      var markerVectorLayer = new ol.layer.Vector({
    	  source: vectorSource,
      });
      map.addLayer(markerVectorLayer);
    </script>
  </body>
</html>
