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

<nav>
	<a href="home.jsp">Home</a>
	<a href=profile.jsp>Profile</a>
	<a href="#" onclick="javascript:{deconnexion();}">Deconnexion</a>
	<div class="animation start-home"></div>
</nav>

	<% 
if(!UserTools.verifSessionOK(session, response)) return;
	System.out.println((Integer)session.getAttribute("id_user"));
UserObject user = new UserObject((Integer)session.getAttribute("id_user"));
	%>


<div id="box">

	<div id="profile" >
		
		<div id="banniere">
		<div id="imageUpload">
			<form  action="javascript:(function(){return;})()" onsubmit="javascript: uploadImage(this)" method="post" enctype="multipart/form-data">
  					<input type="file" name="myimage"/>
    				<input type="submit" name="submit" value="submit">
			</form>
		</div>
			<div id="photo">
			
			</div>
			<div id="nom"><%= user.getFormattedName() %></div>
		
		</div>
	
		
		
		<div id="bio"><div id="msgBio"> <%= user.getBio() %></div>
		<div id="boxModif"></div>
		<div id="divButton"><button onclick="faireApparaitreZoneTexte()">Editer</button></div>
		</div>	
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
		<div id = <%= id %> > <%= e.getTitle()+" "+e.getDatestart()%>	</div>
	<%}
	%></div>
    
  </section>

  <section id="content2">
	  <div id="evenements_futurs"> <%
			  for(Event e : user.getEvenementsFuturs()){
					String id = e.getId();
					%>
					<div id = <%= id %> > <%= e.getTitle()+" "+e.getDatestart() %>	</div>
				<%}
		%></div>
    
  </section>

  <section id="content3">
  	
  	<div id="abonnements"> <%
	for(Integer i : user.getAbonnements().keySet()){
		int id = i.intValue();
		%>
		<div id = <%= id %> > <%= user.getAbonnements().get(i) %>	</div>
	<%}
	%></div>

    
  </section>

  <section id="content4">
  	
  		
	<div id="abonnes"> <%
	for(Integer i : user.getAbonnes().keySet()){
		int id = i.intValue();
		%>
		<div id = <%= id %> > <%= user.getAbonnes().get(i) %>	</div>
	<%}
	%></div>
   
  </section>


	</div>

</div>

<script type="text/javascript" src="fonctions.js"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
</body>
</html>