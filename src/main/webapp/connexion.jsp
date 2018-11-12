<!DOCTYPE html>
<%@page import="database.utils.UserTools"%>
<html lang="fr">

<head>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
	<title>Connexion - Clarinet</title>
	<!-- Bootstrap core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="css/modern-business.css" rel="stylesheet">
</head>

<%
if(UserTools.verifSessionOK(session)) {
	response.sendRedirect("/main.jsp");
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
          	
          	<li class="nav-item active">
              <a class="nav-link" href="/connexion.jsp">Se connecter</a>
            </li>
          
            <li class="nav-item">
              <a class="nav-link" href="/inscription.jsp">S'inscrire</a>
            </li>
            
           
          </ul>
        </div>
      </div>
    </nav>

<!-- Page Content -->
    <div class="container">
	
	<!-- Page Heading/Breadcrumbs -->
      <h1 class="mt-4 mb-3">Connexion
        
      </h1>


	 <div class="row">
	 
	 <div class="col-md-4"></div>
        <div class="col-lg-3 mb-4">
          <form name="sentMessage" id="contactForm" action="validationConnexion.jsp" method="post">
            <div class="control-group form-group">
              <div class="controls">
                <label>Pseudo</label>
                <input type="text" class="form-control" id="pseudo" name="login" required data-validation-required-message="Entrez votre pseudo.">
                <p class="help-block"></p>
              </div>
            </div>
            <div class="control-group form-group">
              <div class="controls">
                <label>Mot de passe</label>
                <input class="form-control" id="mdp" name="password" type="password" required data-validation-required-message="Entrez votre mot de passe.">
              </div>
            </div>
            
            <div id="success"></div>
            <!-- For success/fail messages -->
            <div class="row">
            <div class="col-md-3"></div>
            <button type="submit" class="btn btn-primary" id="connexionButton">Se connecter</button>
            </div>
          </form>
          <p></p>
          
          <div class="row">
            <div class="col-md-3"></div>
          <a href="/inscription.jsp"  id="inscriptionButton">Pas encore inscrit ?</a>
          </div>
        </div>

      </div>


	</div>
	
	 <!-- Bootstrap core JavaScript -->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>
</html>