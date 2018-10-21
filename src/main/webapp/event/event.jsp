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
<title>Insert title here</title>
</head>
<body>
<%= id %>
<%= e.getCity() %>

</body>
</html>