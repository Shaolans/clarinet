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

var cpt = 0;
function nextLoad(){
	cpt++;
	loadList();
}

function prevLoad(){
	cpt--;
	if(cpt <= 0){
		cpt = 0;
	}
	loadList();
}

function searchLoad(){
	cpt = 0;
	loadList();
}

function loadList(){
	$.ajax({
		  type: "GET",
		  url: "/get/events",
		  data: "query="+document.getElementById("myInput").value+"&nbrows=100"+"&startrow="+(cpt*100),
		  success: function(resp){
			  var answer = "";
			  var results = resp["events"];
			  console.log(resp);
			  for(var i = 0; i < results.length; i++){
				  var obj = results[i];
				  var ans = "<li>"+
				  obj.title+" "+
				  obj.start_date+" "+
				  //tags+" "+
				  "<button onclick=\"updateMap(map, '"+obj.address.replace(/,/g,'').replace(/(\r\n\t|\n|\r\t)/gm,"")+"')\">Carte</button>"+
				  "<a href=\"/event/event.jsp?id_event="+obj.id+"\">Lien</a> "+
				  
				  "</li>";
				  answer += ans
			  }
			  document.getElementById("myUL").innerHTML = answer;
		 }
		});
}
