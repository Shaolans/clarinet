<%@page import="user.ConnexionCheck"%>
<jsp:useBean id="obj" class="user.ConnexionObject" />

<jsp:setProperty property="*" name="obj"/> 

<%  

	int id_user=ConnexionCheck.validate(obj);  
	if(id_user>0){  
		out.println("Vous �tes connect�");  
		session.setAttribute("session","TRUE");
		session.setAttribute("id_user", id_user);
		session.setMaxInactiveInterval(1800);
	}  
	else  
		{  
	out.print("Pseudo ou mot de passe erron�");  
%>  

<jsp:include page="connexion.jsp"></jsp:include>
<%  
	}  
%>   