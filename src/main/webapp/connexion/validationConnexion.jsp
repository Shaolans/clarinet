<%@page import="user.ConnexionCheck"%>
<jsp:useBean id="obj" class="user.ConnexionObject"/>

<jsp:setProperty property="*" name="obj"/> 

<%  
	boolean status=ConnexionCheck.validate(obj);  
	if(status){  
		out.println("Vous �tes connect�");  
		session.setAttribute("session","TRUE");  
	}  
	else  
		{  
	out.print("Pseudo ou mot de passe erron�");  
%>  

<jsp:include page="connexion.jsp"></jsp:include>
<%  
	}  
%>   