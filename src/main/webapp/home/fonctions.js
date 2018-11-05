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
		alert(rep.rep);
	}
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


