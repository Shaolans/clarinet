function search(map){
	updateMap(map, document.getElementById("id_ad").value);
}
function updateMap(map, address){
	 var position = unescape(encodeURIComponent(address));
	 console.log(address);
	 $.ajax({
		  type: "GET",
		  url: "https://nominatim.openstreetmap.org/search",
		  data: "q="+position+"&format=json",
		  success: function(resp){
			  console.log(resp);
			  var latitude = parseFloat(resp[0].lat);
			  var longitude = parseFloat(resp[0].lon);
			  
			  map.getView().setCenter(ol.proj.fromLonLat([longitude, latitude]));
			  map.getView().setZoom(17);
			  var marker = new ol.Feature({
            	  geometry: new ol.geom.Point(
            	    ol.proj.fromLonLat([longitude, latitude])
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

		});
	
}