<%@page import="bd.UserTools"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/main.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
</head>
<body>

	<% 
if(!UserTools.verifSessionOK(session)){
	
	response.sendRedirect("/connexion/connexion.jsp"); return;
	}
	
	int id_user = (Integer)session.getAttribute("id_user");

	%>

<nav>
	<a href="/main/main.jsp">Home</a>
	<a href="/home/profile.jsp?id_user=<%=id_user %>">Profile</a>
	<a href="/deconnexion">Deconnexion</a>
	<div class="animation start-home"></div>
</nav>

<script type="text/javascript" src="fonctions.js"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
</body>
</html>