<%@page import="authentification.ConnexionCheck"%>
<jsp:useBean id="obj" class="authentification.ConnexionObject" />

<jsp:setProperty property="*" name="obj"/> 

<%  

	int id_user=ConnexionCheck.validate(obj);  
	if(id_user>0){  
		//out.println("Vous êtes connecté");  
		session.setAttribute("session","TRUE");
		session.setAttribute("id_user", id_user);
		session.setMaxInactiveInterval(1800);
		
   		 response.sendRedirect("/main.jsp");
		
		
	}  
	else  
		{  
			out.print("Pseudo ou mot de passe erroné");  
		%>  

		<jsp:include page="connexion.jsp"></jsp:include>
		<%  
	}  
%>   