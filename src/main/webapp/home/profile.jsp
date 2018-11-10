<%@page import="opendatasoft.Event"%>
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

	<% 
if(!UserTools.verifSessionOK(session)){
	
	response.sendRedirect("/connexion/connexion.jsp"); return;
	}
	

	%>


<jsp:include page="/home/home.jsp"></jsp:include>

	<% 
	int id_user = (Integer)session.getAttribute("id_user");

UserObject user = new UserObject(Integer.parseInt(request.getParameter("id_user")));
	%>
	
	<script>
		var id_user = '<%=id_user%>';
		var user_name = '<%=UserTools.formatName(UserTools.getNameUser(id_user)) %>';
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


<div id="box">

	<div id="profile" >
		
		<div id="banniere">
		<%
		if(id_user==user.getIdUser()){%>
		<div id="imageUpload">
			<form  action="javascript:(function(){return;})()" onsubmit="javascript: uploadImage(this)" method="post" enctype="multipart/form-data">
  					<input type="file" name="myimage"/>
    				<input type="submit" name="submit" value="submit">
			</form>
		</div>
		<%} %>
			<div id="photo">
			<img id="imageUser" src="/getImage" width="125" height ="125"  border="1">
			</div>
			<div id="nom"><%= user.getFormattedName() %></div>
		
		</div>
	
		
		
		<div id="bio"><div id="msgBio"> <%= user.getBio() %></div>
		<%
		if(id_user==user.getIdUser()){%>
		<div id="boxModif"></div>
		<div id="divButton"><button onclick="faireApparaitreZoneTexte()">Editer</button></div>
		<%} %></div>	
	</div>
	

	
	<div id="box_listes">
	
		<input id="tab1" type="radio" name="tabs" checked>
  <label for="tab1">Evenements passés</label>

  <input id="tab2" type="radio" name="tabs">
  <label for="tab2">Evenements futurs</label>

  <input id="tab3" type="radio" name="tabs">
  <label for="tab3">Abonnements</label>

  <input id="tab4" type="radio" name="tabs">
  <label for="tab4">Abonnés</label>

  <section id="content1">
  	<div id="evenements_passes"> <%
	for(Event e : user.getEvenementsPasses()){
		String id = e.getId();
		%>
		<div id = <%= id %> > <a href="../event/event.jsp?id_event=<%=id %>" > <%= e.getTitle()+" "+e.getDatestart() %>	</a></div>
	<%}
	%></div>
    
  </section>

  <section id="content2">
	  <div id="evenements_futurs"> <%
			  for(Event e : user.getEvenementsFuturs()){
					String id = e.getId();
					%>
					<div id = <%= id %> > <a href="/event/event.jsp?id_event=<%=id %>" > <%= e.getTitle()+" "+e.getDatestart() %>	</a></div>
				<%}
		%></div>
    
  </section>

  <section id="content3">
  	
  	<div id="abonnements"> <%
	for(Integer i : user.getAbonnements().keySet()){
		int id = i.intValue();
		%>
		<div id = <%= id %> ><a href="/home/profile.jsp?id_user=<%=id %>" > <%= user.getAbonnements().get(i) %></a>	</div>
	<%}
	%></div>

    
  </section>

  <section id="content4">
  	
  		
	<div id="abonnes"> <%
	for(Integer i : user.getAbonnes().keySet()){
		int id = i.intValue();
		%>
		<div id = <%= id %> ><a href="/home/profile.jsp?id_user=<%=id %>" > <%= user.getAbonnes().get(i) %></a>	</div>
	<%}
	%></div>
   
  </section>


	</div>

</div>

<script type="text/javascript" src="fonctions.js"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
</body>
</html>