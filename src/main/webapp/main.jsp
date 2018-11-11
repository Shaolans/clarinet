<%@page import="bd.UserTools"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="map_geolocalisation.*"%>

<!DOCTYPE html>
<html>
  <head>
  
  <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
	<title>Carte - Clarinet</title>
	<!-- Bootstrap core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="css/modern-business.css" rel="stylesheet">

   <script src="https://cdn.rawgit.com/openlayers/openlayers.github.io/master/en/v5.3.0/build/ol.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>    
    <script src="js/main.js"></script>
    
  </head>
  <body>
  
  
    	<% 
if(!UserTools.verifSessionOK(session)){
	
	response.sendRedirect("/index.jsp"); return;
	}


	%>

		<!-- Navigation -->
    <nav class="navbar fixed-top navbar-expand-lg navbar-dark bg-dark fixed-top">
      <div class="container">
        <a class="navbar-brand" href="/index.jsp">Clarinet</a>
        <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
          <ul class="navbar-nav ml-auto">
          	
          	<li class="nav-item active">
              <a class="nav-link" href="/main.jsp">Carte</a>
            </li>
          	
          	
          	<li class="nav-item">
              <a class="nav-link" href="/profile.jsp">Mon profil</a>
            </li>
          
            <li class="nav-item">
              <a class="nav-link" href="/deconnexion">Se deconnecter</a>
            </li>
            
           
          </ul>
        </div>
      </div>
    </nav>
	
	<div class="row">
	
	 	 <div class="col-lg-4 mb-4">
   	 		
   	 		<!-- Search Widget -->
          <div class="card mb-4">
            <h5 class="card-header">Rechercher</h5>
            <div class="card-body">
              <div class="input-group">
                <input type="text" id="myInput" class="form-control" onkeyup="enterInput(event)" placeholder="Rechercher un événement...">
                <span class="input-group-btn">
                  <button id="searchbtn" class="btn btn-secondary" onclick="loadList()" type="submit">Go !</button>
                </span>
              </div>
            </div>
          </div>
   	 
          <div id="events" class="list-group">
                       
          </div>
          
          <ul class="pagination justify-content-center">
        <li class="page-item">
          <a id="prev" class="page-link" onClick="prevLoad()" aria-label="Previous">
            <span aria-hidden="true">&laquo;</span>
            <span class="sr-only">Previous</span>
          </a>
        </li>
        <div class="col-sm-2"></div>
        <li class="page-item">
          <a id="next" class="page-link" onClick="nextLoad()" aria-label="Next">
            <span aria-hidden="true">&raquo;</span>
            <span class="sr-only">Next</span>
          </a>
        </li>
      </ul>
          
        </div>
  
  	<div class="col-lg-8" id="map"></div>
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
   	
  
        
     </div>
   	
   	
  </body>
  
  	 <!-- Bootstrap core JavaScript -->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  
</html>
