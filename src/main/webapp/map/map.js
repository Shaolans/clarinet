var xmlhttp = new XMLHttpRequest();
var url = "https://nominatim.openstreetmap.org/search?q=";
var address = document.getElementById('address').innerHTML;
address = address.replace(/[ ,]/g, '+');
var format = "&format=json";

xmlhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
        var myArr = JSON.parse(this.responseText);
        myFunction(myArr);
    }
};
xmlhttp.open("GET", url+address+format, true);
xmlhttp.send();

function myFunction(arr) {
    var lon = parseFloat(arr[0].lon);
    var lat = parseFloat(arr[0].lat);
    var map = new ol.Map({
        layers: [
          new ol.layer.Tile({
            source: new ol.source.OSM()
          })
        ],
        target: 'map',
        view: new ol.View({
          center: ol.proj.fromLonLat([lon, lat]),
          zoom: 17
        })
      });
      var marker = new ol.Feature({
    	  geometry: new ol.geom.Point(
    	    ol.proj.fromLonLat([lon, lat])
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
}