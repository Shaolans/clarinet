function deconnexion(){

	$.ajax({ 
        type:"get",
        url:"/deconnexion",
        datatype:"json",
        success:function(rep){
            traiterReponseDeconnexion(rep);
        },
        error:function(jqXMTR, textStatus, errorThrown){
            alert("Pb lors de la transmission des donnÃ©es", "connexion");
        }
    });
	
}


function traiterReponseDeconnexion(rep){
	if(rep.rep===undefined){
		
		alert(rep.err);
	}
	
	else{
		window.location.href = '/connexion/connexion.jsp';
	}
}


function getProfile(){
	$.ajax({ 
        type:"get",
        url:"/deconnexion",
        datatype:"json",
        success:function(rep){
            traiterReponseDeconnexion(rep);
        },
        error:function(jqXMTR, textStatus, errorThrown){
            alert("Pb lors de la transmission des donnÃ©es", "connexion");
        }
    });
	
}

function uploadImage(form){
	
	var image = form.myimage.files[0];
	var formData = new FormData();
	formData.append("photo", image);

	upload(formData);
	
	
}

function upload(image){
	
	$.ajax({ 
        type:"post",
        url:"/upload",
        data:image,
        processData: false,
        contentType: false,
        success:function(rep){
            traiterReponseUpload(rep);
        },
        error:function(jqXMTR, textStatus, errorThrown){
            alert("Pb lors de la transmission des donnees", "upload");
        }
    });
	
}


function traiterReponseUpload(rep){
	if(rep.rep===undefined){
		
		alert(rep.err);
	}
	else{
		location.reload();
	}
}

function LoadImg() {
    var xmlhttp;
    if (window.XMLHttpRequest) { // code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp = new XMLHttpRequest();
    } else { // code for IE6, IE5
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange = function() {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
        	  
        	    document.getElementById('imageUser')
        	    .setAttribute(
        	        'src', 'data:'+xmlhttp.getResponseHeader('content-type')+';base64,'+ xmlhttp.responseText
        	    );
        	   
            
        }
    };    
    xmlhttp.open("GET", '/getImage');
    xmlhttp.send(null);
}

function faireApparaitreZoneTexte(){
	
	
    
    var s ="<form action=\"javascript:(function(){return;})()\" onsubmit=\"javascript: changerTexte(this)\" id=\"confirmationForm\" " +
    		"method=\"post\"><textarea name=\"msg\" rows=\"4\" cols=\"50\"></textarea> " +
    		"<input type=\"submit\" value=\"Envoyer\" class=\"submitButton\">" +
    		"</form>";
    
    var res = document.getElementById("boxModif");
        res.innerHTML=s;
        
        
        res=document.getElementById("divButton");
        res.innerHTML="<button onclick=\"annulerZoneTexte()\">Annuler</button>";
      
        
        
  }


function annulerZoneTexte(){
	  var res = document.getElementById("boxModif");
      res.innerHTML="";
      res=document.getElementById("divButton");
      res.innerHTML="<button onclick=\"faireApparaitreZoneTexte()\">Editer</button>";
}

function changerTexte(form){
	
	var msg = form.msg.value;

	
	$.ajax({ 
        type:"post",
        url:"/bio",
        data:"msg="+msg,
        datatype:"json",
        success:function(rep){
            traiterReponseChangerTexte(rep);
        },
        error:function(jqXMTR, textStatus, errorThrown){
            alert("Pb lors de la transmission des donnÃ©es", "connexion");
        }
    });
}

function getBio(){
	
	var msg = form.msg.value;

	
	$.ajax({ 
        type:"get",
        url:"/bio",
        datatype:"json",
        success:function(rep){
            traiterReponseGetBio(rep);
        },
        error:function(jqXMTR, textStatus, errorThrown){
            alert("Pb lors de la transmission des donnees", "connexion");
        }
    });
}

function traiterReponseChangerTexte(rep){
if(rep.rep===undefined){
	
		alert(rep.err);
	}
	else{
		 var s =rep.rep;
		    
		    var res = document.getElementById("msgBio");
		        res.innerHTML=s;
		        annulerZoneTexte();
		
	}
}


function traiterReponseGetBio(rep){
	if(rep.rep===undefined){
		
			alert(rep.err);
		}
		else{
			 var s =rep.rep;
			    
			    var res = document.getElementById("msgBio");
			        res.innerHTML=s;
			
			
		}
}

function suivre(id_ami){
	
	$.ajax({ 
        type:"post",
        url:"/follow",
        data:"id_ami="+id_ami,
        datatype:"json",
        success:function(rep){
            traiterReponseSuivre(rep);
        },
        error:function(jqXMTR, textStatus, errorThrown){
            alert("Pb lors de la transmission des donnees", "connexion");
        }
    });
	
}

function nePlusSuivre(id_ami){
	$.ajax({ 
        type:"post",
        url:"/unfollow",
        data:"id_ami="+id_ami,
        datatype:"json",
        success:function(rep){
            traiterReponseNePlusSuivre(rep);
        },
        error:function(jqXMTR, textStatus, errorThrown){
            alert("Pb lors de la transmission des donnees", "connexion");
        }
    });
}


function traiterReponseNePlusSuivre(rep){
	if(rep.id_ami===undefined){
		alert(rep.err);
	}
	else{
		res=document.getElementById("following");
	      res.innerHTML="<button id=\"follow\" onClick=\"javascript: suivre("+rep.id_ami+")\">Suivre</button>";
	      element = document.getElementById(id_user);
	      element.parentNode.removeChild(element);
	}
	
}

function traiterReponseSuivre(rep){
	if(rep.id_ami===undefined){
		alert(rep.err);
	}
	else{
		res=document.getElementById("following");
	      res.innerHTML="<button id=\"unfollow\" onClick=\"javascript: nePlusSuivre("+rep.id_ami+")\">Ne plus suivre</button>";
	      res=document.getElementById("abonnes");
	      res.innerHTML+="<div id = "+id_user+" ><a href=\"/profile.jsp?id_user="+id_user+"\" > "+user_name+"</a>	</div>";
	}
	
}


