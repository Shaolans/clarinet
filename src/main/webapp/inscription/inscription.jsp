<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8"/>
		<title>Inscription</title>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="icon" type="image/png" href="../connexion/images/icons/favicon.ico"/>
		<link rel="stylesheet" type="text/css" href="../connexion/css/util.css" />
		<link rel="stylesheet" type="text/css" href="../inscription/css/main.css" />
		<link rel="stylesheet" type="text/css" href="../inscription/css/style.css" />
	</head>
	<style>
		body{
			background-image: url('../connexion/images/bg-01.jpg');
			-webkit-background-size: cover;
			-moz-background-size: cover;
			-o-background-size: cover;
		}
	</style>
	<body>
		<form action="/inscription" method="post">
			<div class="container-login100">
				<div class="wrap-login100 p-l-55 p-r-55 p-t-65 p-b-54">
					<span class="login100-form-title p-b-50" style="font-size:35px;">
						Créez votre compte
					</span>
					
					
					<div class="wrap-input100 m-b-23">
						<span class="label-input">Pseudo <span class="requis">*</span></span>
						<input class="input100" type="text" name="user_pseudo" value="${param.user_pseudo}" placeholder="3-32 caractère(s)"/>
						<span class="focus-input100"></span>
					</div>
					<span class="erreur">${erreurs['pseudo']}</span>
					
					
					<div class="wrap-input100 m-b-23">
						<span class="label-input">Mot de passe <span class="requis">*</span></span>
						<input class="input100" type="password" name="user_pwd" placeholder="6-32 caractère(s)"/>
						<span class="focus-input100"></span>
					</div>
					<span class="erreur">${erreurs['pwd']}</span>
					
					
					<div class="wrap-input100 m-b-23">
						<span class="label-input">Confirmation mot de passe <span class="requis">*</span></span>
						<input class="input100" type="password" name="user_confirm_pwd" placeholder="6-32 caractère(s)"/>
						<span class="focus-input100"></span>
					</div>
					<span class="erreur">${erreurs['confirm']}</span>
					
					<div class="wrap-input100 m-b-23">
						<span class="label-input">Prénom </span>
						<input class="input100" type="text" name="user_first_name" value="${param.user_first_name}" />
						<span class="focus-input100"></span>
					</div>
					<span class="erreur">${erreurs['prenom']}</span>
					<div class="wrap-input100 m-b-23">
						<span class="label-input">Nom</span>
						<input class="input100" type="text" name="user_last_name" value="${param.user_last_name}" />
						<span class="focus-input100"></span>
					</div>
					<span class="erreur">${erreurs['nom']}</span>
					
					<div class="wrap-input100 m-b-23">
						<span class="label-input">Adresse email</span>
						<input class="input100" type="email" name="user_email" value="${param.user_email}">
						<span class="focus-input100"></span>
					</div>
					<span class="erreur">${erreurs['email']}</span>
					<div class="container-login100-form-btn">
						<div class="wrap-login100-form-btn">
							<div class="login100-form-bgbtn"></div>
							<button class="login100-form-btn">
								S'inscrire
							</button>
						</div>
					</div>
				</div>
			</div>
		</form>
	</body>
</html>