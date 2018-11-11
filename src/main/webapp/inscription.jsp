<!DOCTYPE html>
<%@page import="bd.UserTools"%>
<html lang="fr">

<head>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
	<title>Inscription - Clarinet</title>
	<!-- Bootstrap core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="css/modern-business.css" rel="stylesheet">
</head>

<%
if(UserTools.verifSessionOK(session)) {
	response.sendRedirect("/main/main.jsp");
	return ;
}
%>

<body>

	<!-- Navigation -->
    <nav class="navbar fixed-top navbar-expand-lg navbar-dark bg-dark fixed-top">
      <div class="container">
        <a class="navbar-brand" href="/index.jsp">Clarinet</a>
        <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
          <ul class="navbar-nav ml-auto">
          	
          	<li class="nav-item">
              <a class="nav-link" href="/connexion.jsp">Se connecter</a>
            </li>
          
            <li class="nav-item active">
              <a class="nav-link" href="/inscription.jsp">S'inscrire</a>
            </li>
            
           
          </ul>
        </div>
      </div>
    </nav>

<!-- Page Content -->
    <div class="container">

	<!-- Page Heading/Breadcrumbs -->
      <h1 class="mt-4 mb-3">Inscription
        
      </h1>

	 <div class="row">
	 <div class="col-md-4"></div>
        <div class="col-lg-3 mb-4">
          <form name="sentMessage" id="contactForm" action="/inscription" method="post">
            <div class="control-group form-group">
              <div class="controls">
                <label>Pseudo</label>
                <input type="text" class="form-control" id="pseudo" name="user_pseudo" value="${param.user_pseudo}" required data-validation-required-message="Entrez votre pseudo.">
                <p class="help-block"></p>
              </div>
            </div>
            <div class="control-group form-group">
              <div class="controls">
                <label>Mot de passe</label>
                <input class="form-control" id="mdp" name="user_pwd" type="password" required data-validation-required-message="Entrez votre mot de passe.">
              </div>
            </div>
            
            <div class="control-group form-group">
              <div class="controls">
                <label>Confirmation mot de passe</label>
                <input class="form-control" id="confirmation_mdp" name="user_confirm_pwd" type="password" required data-validation-required-message="Confirmez votre mot de passe.">
              </div>
            </div>
            
             <div class="control-group form-group">
              <div class="controls">
                <label>Prénom</label>
                <input class="form-control" id="prenom" name="user_first_name" type="text" value="${param.user_first_name}" required data-validation-required-message="Entrez votre prénom.">
              </div>
            </div>
            
             <div class="control-group form-group">
              <div class="controls">
                <label>Nom</label>
                <input class="form-control" id="nom" name="user_last_name" type="text" value="${param.user_last_name}" required data-validation-required-message="Entrez votre nom.">
              </div>
            </div>
            
            
            
            <div id="success">
            	<span class="erreur">${erreurs['pseudo']}</span>
            	<span class="erreur">${erreurs['pwd']}</span>
            	<span class="erreur">${erreurs['confirm']}</span>
            	<span class="erreur">${erreurs['prenom']}</span>
            	<span class="erreur">${erreurs['nom']}</span>
            </div>
            <!-- For success/fail messages -->
            <div class="row">
            <div class="col-md-4"></div>
            	<button type="submit" class="btn btn-primary" id="inscriptionButton">S'inscrire</button>
         </div>
          </form>
          
          
          
        </div>

      </div>


	</div>
	
	 <!-- Bootstrap core JavaScript -->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>
</html>


