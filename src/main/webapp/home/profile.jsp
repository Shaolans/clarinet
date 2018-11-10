<%@page import="opendatasoft.Event"%>
<%@page import="bd.UserTools"%>
<%@page import="user.UserCheck"%>
<%@page import="user.UserObject"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" media="all" href="../chat/css/chatbox.css">
<link type="text/css" rel="stylesheet" media="all" href="../chat/css/animate-custom.css">
<link type="text/css" rel="stylesheet" media="all" href="../chat/css/style.css">
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
  <label for="tab1">Evenements pass�s</label>

  <input id="tab2" type="radio" name="tabs">
  <label for="tab2">Evenements futurs</label>

  <input id="tab3" type="radio" name="tabs">
  <label for="tab3">Abonnements</label>

  <input id="tab4" type="radio" name="tabs">
  <label for="tab4">Abonn�s</label>

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
		String user_name = user.getAbonnements().get(i);
		%>
		<div id = <%= id %> >
		<a href="/home/profile.jsp?id_user=<%=id %>" > <%= user.getAbonnements().get(i) %></a>	
		<button onclick="doChat('<%=user_name%>',<%=id%>)">Chat</button>
		</div>
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

<script type="text/javascript" src="../chat/js/jquery-1.10.2.js"></script>
<script type="text/javascript" src="../chat/js/chatbox.js"></script>
<script type="text/javascript">
	$(function(){
		var url = "ws://" + location.hostname + ":" + location.port + "/chat";
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
</body>
</html>