<%@page import="java.util.List"%>
<%@page import="opendatasoft.Event"%>
<%@page import="database.utils.UserTools"%>
<%@page import="authentification.UserCheck"%>
<%@page import="authentification.UserObject"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<% 
if(!UserTools.verifSessionOK(session)){
	
	response.sendRedirect("/connexion.jsp"); return;
	}
	

	%>



	<% 
	int id_user = (Integer)session.getAttribute("id_user");
	UserObject  user ;
	if(request.getParameter("id_user")==null){
		user = new UserObject(id_user);
	}
	else{
		user = new UserObject(Integer.parseInt(request.getParameter("id_user")));
	}

	%>


<head>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
	<title>Profile de <%= user.getFormattedName() %> - Clarinet</title>
	<!-- Bootstrap core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="css/modern-business.css" rel="stylesheet">		
	
	
	<link type="text/css" rel="stylesheet" media="all" href="/chat/css/chatbox.css">
<link type="text/css" rel="stylesheet" media="all" href="/chat/css/animate-custom.css">
<link type="text/css" rel="stylesheet" media="all" href="/chat/css/style.css">

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
          	
          	
          	<li class="nav-item active">
              <a class="nav-link" href="/profile.jsp">Mon profil</a>
            </li>
          
            <li class="nav-item">
              <a class="nav-link" href="/deconnexion">Se deconnecter</a>
            </li>
            
           
          </ul>
        </div>
      </div>
    </nav>
		
<% 
List<String> list = UserTools.getNameUser(id_user);
String userFormattedName =  UserTools.formatName(list);
%>
	
	<script>
		var id_user = '<%=id_user%>';
		var user_name = '<%=userFormattedName%>';
	</script>
	
<div id = "following"> 
<%
if(id_user!=user.getIdUser()){
	if(user.getAbonnes().get(id_user)!=null){
		%>
		<button id="unfollow" onClick="javascript: nePlusSuivre(<%=user.getIdUser() %>)">Ne plus suivre</button>
	<%	 
	}
	else{
		%>
		<button id="follow" onClick="javascript: suivre(<%=user.getIdUser() %>)">Suivre</button>
		<%
	}
}
%>
</div>



<div class="row" >

	<div class="col-lg-3" >
	
	<!-- Blog Post -->
          <div class="card mb-4"  id="profile">
          
            <img class="card-img-top" id="imageUser" src="/getImage" width="125" height ="125"  border="1" alt="Image de l'événement">
            <div class="card-body">
              <h2 class="card-title"><%= user.getFormattedName() %></h2>
              <p class="card-text"><%= user.getBio() %><%
		if(id_user==user.getIdUser()){%>
		<div id="boxModif"></div>
		<div id="divButton"><button onclick="faireApparaitreZoneTexte()">Editer</button></div>
		<%} %></p>
            </div>
            <div class="card-footer text-muted">
            	<%if(id_user==user.getIdUser()){%>
				<div id="imageUpload">
					<form  action="javascript:(function(){return;})()" onsubmit="javascript: uploadImage(this)" method="post" enctype="multipart/form-data">
		  					<input type="file" name="myimage"/>
		    				<input type="submit" name="submit" value="submit">
					</form>
				</div>
				<%} %>
              
            
            </div>
          </div>
		
			

	</div>
	
	

	
	<div class="col-lg-8" id="accordion" role="tablist" aria-multiselectable="true">
        <div class="card">
          <div class="card-header" role="tab" id="headingOne">
            <h5 class="mb-0">
              <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">Evénements passés</a>
            </h5>
          </div>

          <div id="collapseOne" class="collapse show" role="tabpanel" aria-labelledby="headingOne">
            <div class="card-body">
            <div id="evenements_passes"> <%
	for(Event e : user.getEvenementsPasses()){
		String id = e.getId();
		%>
		<div id = <%= id %> > <a href="/event.jsp?id_event=<%=id %>" > <%= e.getTitle()+" "+e.getDatestart() %>	</a></div>
	<%}
	%></div>
            </div>
          </div>
        </div>
        <div class="card">
          <div class="card-header" role="tab" id="headingTwo">
            <h5 class="mb-0">
              <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">Evénements futurs
              </a>
            </h5>
          </div>
          <div id="collapseTwo" class="collapse" role="tabpanel" aria-labelledby="headingTwo">
            <div class="card-body">
            <div id="evenements_futurs"> <%
			  for(Event e : user.getEvenementsFuturs()){
					String id = e.getId();
					%>
					<div id = <%= id %> > <a href="/event.jsp?id_event=<%=id %>" > <%= e.getTitle()+" "+e.getDatestart() %>	</a></div>
				<%}
		%></div>
            </div>
          </div>
        </div>
        <div class="card">
          <div class="card-header" role="tab" id="headingThree">
            <h5 class="mb-0">
              <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">Abonnements</a>
            </h5>
          </div>
          <div id="collapseThree" class="collapse" role="tabpanel" aria-labelledby="headingThree">
            <div  id="abonnements" class="card-body">
             <%
	for(Integer i : user.getAbonnements().keySet()){
		int id = i.intValue();
		String user_name = user.getAbonnements().get(i);
		%>
		<div id = <%= id %> >
		<a href="/profile.jsp?id_user=<%=id %>" > <%= user.getAbonnements().get(i) %></a>	
		<button onclick="doChat('<%=user_name%>',<%=id%>)">Chat</button>
		</div>
	<%}
	%>
            </div>
          </div>
        </div>
        
        <div class="card">
          <div class="card-header" role="tab" id="headingFour">
            <h5 class="mb-0">
              <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseFour" aria-expanded="false" aria-controls="collapseFour">Abonnés</a>
            </h5>
          </div>
          <div id="collapseFour" class="collapse" role="tabpanel" aria-labelledby="headingFour">
            <div id="abonnes" class="card-body">
            <%
	for(Integer i : user.getAbonnes().keySet()){
		int id = i.intValue();
		%>
		<div id = <%= id %> ><a href="/profile.jsp?id_user=<%=id %>" > <%= user.getAbonnes().get(i) %></a>	</div>
	<%}
	%>
            </div>
          </div>
        </div>
        
      </div>
	
	
</div>


<script type="text/javascript" src="fonctions.js"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>

<script type="text/javascript" src="/chat/js/jquery-1.10.2.js"></script>
<script type="text/javascript" src="/chat/js/chatbox.js"></script>
<script type="text/javascript">
	$(function(){
		if (window.location.protocol == "https:") {
		  var ws_scheme = "wss://";
		} else {
		  var ws_scheme = "ws://"
		};
		var url = ws_scheme + location.host + "/chat";
		var ws = new WebSocket(url);
        ws.onopen = function(){
        	var msg = {
        		type: 'login',
        		from: user_name,
        		from_id : id_user,
        		to: '',
        		to_id: '',
        		content: '',
        		time: ''
        	};
        	console.log(JSON.stringify(msg));
        	ws.send(JSON.stringify(msg));
        };
        ws.onmessage = function(e){
        	console.log(e.data);
       		var msg = JSON.parse(e.data);
           	if (msg.from_id == ""){
           		$.chatbox(Number(msg.from_id)).message(e.data,'system');
           	}
           	else{
           		$.chatbox(Number(msg.from_id)).message(e.data,'from');
           	}
        };
        ws.onerror = function(e){};
        ws.onclose = function(e){
        	console.log('Close: '+e.code+' '+e.reason+' '+ e.wasClean);
        };
        
	    $.chatbox.globalOptions = {
	        id:id_user,
	        name:user_name,
	        debug:true,
	        websocket: ws
	    }
	});
</script>
<script type="text/javascript">
	function doChat(user_name, user_id){
		$.chatbox({
            id:user_id,
            name:user_name,
            title:'Chat with '+user_name,
            type:'private'
        });
	}
</script>

 <!-- Bootstrap core JavaScript -->
   <!-- Ne fait pas fonctionner le chat  <script src="vendor/jquery/jquery.min.js"></script> -->
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>
</html>