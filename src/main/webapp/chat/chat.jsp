<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link type="text/css" rel="stylesheet" media="all" href="css/chatbox.css">
    <link type="text/css" rel="stylesheet" media="all" href="css/animate-custom.css">
    <link type="text/css" rel="stylesheet" media="all" href="css/style.css">
</head>

<body>
<div id="main-container">
	<div>
	    <h4>Dialoguer</h4>
	    <input type="text" id="chatbox-id" placeholder="Dialog id">
	    <input type="text" id="chatbox-user" placeholder="Receiver id">
	    <input type="button" id="do-chat" value="Chat">
	</div>
	<div>
	    <input type="button" id="set-show" value="Set Show">
	    <input type="button" id="set-hide" value="Set Hide">
	    <input type="button" id="set-destroy" value="Set Destroy">
	    <input type="button" id="set-blink" value="Set Blink">
    	<input type="button" id="set-animate" value="Set Animate">
	</div>
</div>
<script type="text/javascript" src="js/jquery-1.10.2.js"></script>
<script type="text/javascript" src="js/chatbox.js"></script>
<script type="text/javascript">
	$(function(){
		var url = "ws://" + location.hostname + ":" + location.port + "/chat";
        var ws = new WebSocket(url);
        ws.onopen = function(){
        	var msg = {
        		type: 'login',
        		from: <%=session.getAttribute("login")%>,
        		from_id : <%=session.getAttribute("id_user")%>,
        		/* from: 'Moi',
        		from_id: '1000', */
        		to: '',
        		to_id: '',
        		content: '',
        		time: ''
        	};
        	ws.send(JSON.stringify(msg));
        };
        ws.onmessage = function(e){
        	console.log(e.data);
       		var msg = JSON.parse(e.data);
           	if (msg.from_id == ""){
           		$.chatbox(msg.to_id).message(e.data,'system');
           	}
           	else{
           		$.chatbox(msg.to_id).message(e.data,'from');
           	}
        };
        ws.onerror = function(e){};
        ws.onclose = function(e){
        	ws = null;
        };
        
	    $.chatbox.globalOptions = {
	        id:<%=session.getAttribute("id_user")%>,
	        name:<%=session.getAttribute("login")%>,
/* 	        id:'1000',
	        name:'Moi', */
	        debug:true,
	        websocket: ws
	    }
	    
	    $('#do-chat').click(function(){
	        $.chatbox({
	            id:$("#chatbox-id").val(),
	            name:$("#chatbox-user").val(),
	            title:'Chat with '+$("#chatbox-user").val(),
	            type:'private'
	        });
	    });
	    
	    $('#do-room-chat').click(function(){
	        $.chatbox({
	            id:$("#chatbox-id").val(),
	            name:$("#chatbox-room").val(),
	            title:'Room chat of '+$("#chatbox-room").val(),
	            type:'room'
	        });
	    });

/* 	    $('#set-show').click(function(){
	        $.chatbox(Number($("#chatbox-id").val())).show();
	    });
	    $('#set-hide').click(function(){
	        $.chatbox(Number($("#chatbox-id").val())).hide();
	    });
	    $('#set-destroy').click(function(){
	        $.chatbox(Number($("#chatbox-id").val())).destroy();
	    });
	    $('#set-blink').click(function(){
	        $.chatbox(Number($("#chatbox-id").val())).blink();
	    });
	    $('#set-animate').click(function(){
	        $.chatbox(Number($("#chatbox-id").val())).animate();
	    }); */
	});
</script>
</body>

</html>