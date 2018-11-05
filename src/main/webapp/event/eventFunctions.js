
function handleEventSub(eventid){
	 $.ajax({
		  type: "POST",
		  url: "/event/participate",
		  data: "id_event="+eventid,
		  success: function(res){
			  
			  traiterReponseSuivre(res);
			  
		 },
		error:function(jqXMTR, textStatus, errorThrown){
         alert("Pb lors de la transmission des donnees", "connexion");
    	}
	 

		});

}

function traiterReponseSuivre(res) {
	
	if(res.rep===undefined){
		alert(res.err);
	}
	else{
		var s = "<button id=\"event_sub_btn\" onclick=\"nePlusParticiperEvent(ide)\" data-animation=\"ripple\">" +
				"\
			Ne plus participer\
			</button>";
		document.getElementById("participerDiv").innerHTML = s;
	}
	
}

function nePlusParticiperEvent(eventid){
	
	$.ajax({
		  type: "POST",
		  url: "/event/noParticipate",
		  data: "id_event="+eventid,
		  success: function(res){
			  
			  traiterReponseNePlusParticiper(res);
			  
		 },
		error:function(jqXMTR, textStatus, errorThrown){
       alert("Pb lors de la transmission des donnees", "connexion");
  	}
	 

		});
	
}

function traiterReponseNePlusParticiper(res) {
	
	if(res.rep===undefined){
		alert(res.err);
	}
	else{
		var s = "<button id=\"event_sub_btn\" onclick=\"handleEventSub(ide)\" data-animation=\"ripple\">" +
				"\
			Participer\
			</button>";
		document.getElementById("participerDiv").innerHTML = s;
	}
	
}


