<%@page import="org.apache.commons.lang.StringEscapeUtils" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="opendatasoft.*,database.utils.*, java.util.*,authentification.*"%>

	<% int id_user;
if(!UserTools.verifSessionOK(session)){
	
	response.sendRedirect("/connexion.jsp"); return;
	}
else
	id_user = (Integer)session.getAttribute("id_user");

	%>
	
	


<%
	String id = request.getParameter("id_event");
	Event e = OpendatasoftRequest.eventById(id);
%>




<!DOCTYPE html>
<html>
	<script>
	<% 
	String userFormattedName =  UserTools.formatName(UserTools.getNameUser(id_user));
	userFormattedName = StringEscapeUtils.escapeHtml(userFormattedName);
	userFormattedName = userFormattedName.replaceAll("\"","&quote;");
	userFormattedName = userFormattedName.replaceAll("\'", "\\\\'");
	%>
	var id_user = '<%=id_user%>';
	var user_name = '<%=userFormattedName %>';
		var ide = '<%=id%>';
	</script>
	

	<head>
		<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
	<title><%= e.getTitle() %> - Clarinet</title>
	<!-- Bootstrap core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="css/modern-business.css" rel="stylesheet">		
	
	<link rel="stylesheet" type="text/css" href="css/main.css">
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

	</head>
	<body>
	
	
	
	
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
      <h1 class="mt-4 mb-3"><%= e.getTitle() %>  </h1>
      
      <div class="row">

        <!-- Blog Entries Column -->
        <div class="col-md-8">

          <!-- Blog Post -->
          <div class="card mb-4">
          
            <img class="card-img-top" src=<%=e.getImage() %> alt="Image de l'événement">
            <div class="card-body">
              <h2 class="card-title"><%= e.getDescription() %></h2>
              <p class="card-text"><%= e.getFreetext() %></p>
            </div>
            <div class="card-footer text-muted">
              <b>Date de début :</b> <%= e.getDatestart() %> <br> <br>
				<b>Date de fin :</b> <%= e.getDateend() %> <br> <br>
				<b>Début :</b> <%= e.getTimeInfo() %> <br> <br>
				<b>Prix :</b> <%= e.getPrice() %> <br> <br>
				<b>Adresse :</b> <%= e.getAddress() %> <br> <br>
				<b>Ville :</b> <%= e.getCity() %> <br> <br>
				<b>Département :</b> <%= e.getDepartment() %> <br> <br>
				<b>Région :</b> <%= e.getRegion() %> <br> <br>
				<b>Site internet :</b> <a href="<%=e.getLink() %>" ><%=e.getLink() %></a> <br> <br>
            
            </div>
          </div>
          
     </div>
		
		 <!-- Sidebar Widgets Column -->
        <div class="col-md-4">

          <!-- Search Widget -->
          <div class="card mb-4" id="participerDiv">
          
          <%
		
		
		if(UserTools.getEvenementId(id_user, id)){
			%>
			
	
			<button  id="event_sub_btn" class="btn btn-primary" onclick="nePlusParticiperEvent(ide)" type="button">
				Ne plus participer
			</button>
	
			
			<%
			
			
		}
		else{
			%>
			
			<button id="event_sub_btn" class="btn btn-primary" onclick="handleEventSub(ide)" type="button">
			Participer
			</button>
		
			
			<%
			
		}
		
		%>
            
            

          </div>

          <!-- Categories Widget -->
          <div class="card my-4">
            <h5 class="card-header">Participants</h5>
            <div class="card-body">
              <div class="row">
                <div class="col-lg-6">
                  <ul id="participants" class="list-unstyled mb-0">
                  
                  <%
			List<Integer> id_users = UserTools.getParticipants(id);
			for(Integer id_u: id_users){
				String name = UserTools.formatName(UserTools.getNameUser(id_u));

				%>
				<li id = <%= id_u %> ><a href="/profile.jsp?id_user=<%=id_u %>" > <%= name %></a>	</li>
				
				<%
			}
				%>
                  
                    
                  </ul>
                </div>
                
              </div>
            </div>
          </div>
		
		
		
		
	</div>
	</div>
	</div>		
				
	</body>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>    
		<script src="js/eventFunctions.js"></script>
		<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
		
	 <!-- Bootstrap core JavaScript -->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	
</html>


