<%@page import="bd.UserTools"%>
<%@page import="user.UserCheck"%>
<%@page import="user.UserObject"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/main.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Profile</title>
</head>
<body>

<nav>
	<a href="#">Home</a>
	<a href=profile.jsp>Profile</a>
	<a href="#" onclick="javascript:{deconnexion();}">Deconnexion</a>
	<div class="animation start-home"></div>
</nav>

<div>Profile ici</div>

<% 
if(!UserTools.verifSessionOK(session, response)) return;
UserObject user = new UserObject();
	user.setIdUser((Integer)session.getAttribute("id_user"));
	UserCheck.chargerUserAbonnement(user);
	%> <div id="abonnements"> <%
	for(Integer i : user.getAbonnements().keySet()){
		int id = i.intValue();
		%>
		<div id_user = <%= id %> > <%= user.getAbonnements().get(i) %>	</div>
	<%}
	%></div>
	
	<div id="abonnés"> <%
	for(Integer i : user.getAbonnes().keySet()){
		int id = i.intValue();
		%>
		<div id_user = <%= id %> > <%= user.getAbonnes().get(i) %>	</div>
	<%}
	%></div>
	


<script type="text/javascript" src="fonctions.js"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
</body>
</html>