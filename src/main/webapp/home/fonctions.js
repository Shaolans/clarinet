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
	
	else
		alert(rep.rep);
}
