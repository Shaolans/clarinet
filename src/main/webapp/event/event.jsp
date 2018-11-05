<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="opendatasoft.*, bd.*, java.util.*, user.*"%>



<%
	String id = request.getParameter("id_event");
	Event e = OpendatasoftRequest.eventById(id);
%>




<!DOCTYPE html>
<html>
	<script>
		var ide = '<%=id%>';
	</script>

	<head>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>    
		<script src="eventFunctions.js"></script>
		<link rel="stylesheet" type="text/css" href="css/main.css">
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta charset="ISO-8859-1">
		<title><%= e.getTitle() %></title>
	</head>
	<body>
		<nav>
			<a href="#">Home</a>
			<a href=profile.jsp>Profile</a>
			<a href="#" onclick="javascript:{deconnexion();}">Deconnexion</a>
			<div class="animation start-home"></div>
		</nav>
		
		<script type="text/javascript" src="/home/fonctions.js"></script>
		<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
		
		<% 
			if(!UserTools.verifSessionOK(session, response)) return;
				System.out.println((Integer)session.getAttribute("id_user"));
	%>
		
		<h2>Évènement</h2>
		<b class="hr"></b>
		<b>Référence:</b> <%= e.getId() %> <br> <br>
		<b>Titre:</b> <%= e.getTitle() %> <br> <br>
		<b>Date de début:</b> <%= e.getDatestart() %> <br> <br>
		<b>Date de fin:</b> <%= e.getDateend() %> <br> <br>
		<b>Début:</b> <%= e.getTimeInfo() %> <br> <br>
		<b>Prix:</b> <%= e.getPrice() %> <br> <br>
		<b>Adresse:</b> <%= e.getAddress() %> <br> <br>
		<b>Ville:</b> <%= e.getCity() %> <br> <br>
		<b>Département:</b> <%= e.getDepartment() %> <br> <br>
		<b>Région:</b> <%= e.getRegion() %> <br> <br>
		<b>Description:</b> <%= e.getDescription() %> <br> <br>
		<b>Contenu libre:</b> <%= e.getFreetext() %> <br> <br>
		<%
			if(e.getLink() != ""){
				out.println("<b>Lien:</b> <a href=\""+e.getLink()+"\" >"+e.getTitle()+"</a> <br> <br>");
			}
		%>
		
		<b>Tags: </b>
		
		<%
			if(e.getTags().size() > 0){
				for(int i = 0; i < e.getTags().size()-1; i++){
					out.print(e.getTags().get(i)+", ");
				}
				out.println(e.getTags().get(e.getTags().size()-1));
			}
		%>
		<br> <br>
		<%
			if(e.getImage() != ""){
				out.print("<img src=\""+e.getImage()+"\"/>");
			}
		%>
		<br>  <br>
		
		<%
		int id_user = (Integer)session.getAttribute("id_user");
		
		if(UserTools.getEvenementId(id_user, id)){
			%>
			
			<div class="container">
			<div id="participerDiv"><button id="event_sub_btn" onclick="nePlusParticiperEvent(ide)" data-animation="ripple">
				Ne plus participer
			</button></div>
		</div>
			
			<%
			
			
		}
		else{
			%>
			
			<div class="container">
			<div id="participerDiv"><button id="event_sub_btn" onclick="handleEventSub(ide)" data-animation="ripple">
			Participer
			</button></div>
		</div>
			
			<%
			
		}
		
		%>
		
		
		
		<br>
		<h2>Participants</h2>

		<%
			List<Integer> id_users = UserTools.getParticipants(id);
			for(Integer id_u: id_users){
				String name = UserTools.formatName(UserTools.getNameUser(id_u));
				/*out.println(name);
				out.println("<a href=\"/home/profile.jsp\" class=\"myButton\">Profile</a>");
				out.println("<a href=\"/home/profile.jsp\" class=\"myButton\">Message</a>");
				out.println("<button class=\"myButton\">Suivre</button>");
				out.println("<br>");
				out.println("<br>");*/
				%>
				<div id="<%=id_u %>"><%=name %></div>
				<%
			}
				%>
				
	</body>
	<script src="css.js"></script>
</html>


