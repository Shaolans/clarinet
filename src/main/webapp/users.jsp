<%@page import="authentification.UserPrimitiveContainer"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="database.utils.UserTools"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
  
  <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
	<title>Trouvez des utilisateurs - Clarinet</title>
	<!-- Bootstrap core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="css/modern-business.css" rel="stylesheet">
   <script src="https://cdn.rawgit.com/openlayers/openlayers.github.io/master/en/v5.3.0/build/ol.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>    
    <script src="js/main.js"></script>
    <style>
	    #myInput {
	    background-image: url('/css/searchicon.png'); /* Add a search icon to input */
	    background-position: 10px 12px; /* Position the search icon */
	    background-repeat: no-repeat; /* Do not repeat the icon image */
	    width: 100%; /* Full-width */
	    font-size: 16px; /* Increase font-size */
	    padding: 12px 20px 12px 40px; /* Add some padding */
	    border: 1px solid #ddd; /* Add a grey border */
	    margin-bottom: 12px; /* Add some space below the input */
		}
		
		#myUL {
		    /* Remove default list styling */
		    list-style-type: none;
		    padding: 0;
		    margin: 0;
		}
		
		#myUL li a {
		    border: 1px solid #ddd; /* Add a border to all links */
		    margin-top: -1px; /* Prevent double borders */
		    background-color: #f6f6f6; /* Grey background color */
		    padding: 12px; /* Add some padding */
		    text-decoration: none; /* Remove default text underline */
		    font-size: 18px; /* Increase the font-size */
		    color: black; /* Add a black text color */
		    display: block; /* Make it into a block element to fill the whole list */
		}
		
		#myUL li a:hover:not(.header) {
		    background-color: #eee; /* Add a hover effect to all links, except for headers */
		}
    </style>
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
          	
          	
          	
          	<li class="nav-item">
              <a class="nav-link" href="/main.jsp">Carte</a>
            </li>
            
            <li class="nav-item active" >
              <a class="nav-link" href="/users.jsp">Utilisateurs</a>
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
    
    <div class="container">

      <!-- Page Heading/Breadcrumbs -->
      <h1 class="mt-4 mb-3">Trouvez des utilisateurs !
       
      </h1>
       <h2>Entrez un pseudo dans la barre de recherche</h2>

<div class="row">

<div class="col-lg-1"></div>       
       
<div class="sidebar-nav-fixed col-lg-8">
    <div class="column">
    <div class="card-body">
              <div class="input-group">
              <input type="text" id="searchuser" onkeyup="myFunction()" placeholder="Rechercher par nom.." title="Taper un nom">
                <span class="input-group-btn">
                  <button id="searchbtn" class="btn btn-secondary" onclick="myFunction()" type="submit">Go !</button>
                </span>
              </div>
              
            </div>
    
    
		

		<ul id="myUL">
			<%
        	List<UserPrimitiveContainer> users = UserTools.getUsers();
        	for(UserPrimitiveContainer u: users){
        		List<String> format = new ArrayList<String>();
        		format.add(u.getLogin());
        		format.add(u.getFname());
        		format.add(u.getLname());
        		out.println("<li><a href=\"profile.jsp?id_user="+u.getId()+"\">"+UserTools.formatName(format)+"</a></li>");
        	}
        	%>
		</ul>
    </div>
      

    </div>


</div>


</body>
</html>