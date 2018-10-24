
function handleEventSub(eventid){
	 $.ajax({
		  type: "POST",
		  url: "/event/participate",
		  data: "id_event="+eventid,
		  success: function(msg){
			  document.getElementById("event_sub_btn").innerHTML = msg.resp;
			  document.getElementById("event_sub_btn").setAttribute("disabled", true);
		 }

		});

}

