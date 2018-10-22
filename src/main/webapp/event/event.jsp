<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="opendatasoft.*"%>

<%
	String id = request.getParameter("id_event");
	Event e = OpendatasoftRequest.eventById(id);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title><%= e.getTitle() %></title>
</head>
<body>
<h2>Event</h2>
id event: <%= e.getId() %> <br>
title: <%= e.getTitle() %> <br>
start: <%= e.getDatestart() %> <br>
end: <%= e.getDateend() %> <br>
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

<h2>Participants</h2>

</body>
</html>