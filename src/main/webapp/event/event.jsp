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
		
		
		<h2>Event</h2>
		id event: <%= e.getId() %> <br>
		title: <%= e.getTitle() %> <br>
		start: <%= e.getDatestart() %> <br>
		end: <%= e.getDateend() %> <br>
		time_info: <%= e.getTimeInfo() %> <br>
		Price: <%= e.getPrice() %> <br>
		Address: <%= e.getAddress() %> <br>
		department: <%= e.getDepartment() %> <br>
		city: <%= e.getCity() %> <br>
		Region: <%= e.getRegion() %> <br>
		Description: <%= e.getDescription() %> <br>
		Free text: <%= e.getFreetext() %> <br>
		<%
			if(e.getLink() != ""){
				out.println("Link: <a href=\""+e.getLink()+"\" >"+e.getTitle()+"</a> <br>");
			}
		%>
		
		
		<%
			if(e.getImage() != ""){
				out.print("<img src=\""+e.getImage()+"\"/>");
			}
		%>
		<br>
		<div class="container">
			<button id="event_sub_btn" onclick="handleEventSub(ide)" data-animation="ripple">
			Participer
			</button>
		</div>
		
		<br>
		<h2>Participants</h2>
		<%
			List<Integer> id_users = UserTools.getParticipants(id);
			for(Integer id_u: id_users){
				String name = UserTools.getNameUser(id_u);
				out.println(name+"<br>");
			}
		%>
	</body>
	<script src="css.js"></script>
</html>


