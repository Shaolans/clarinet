<!DOCTYPE html>
<html>
<head>
    <title>Chatbox</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link type="text/css" rel="stylesheet" media="all" href="css/jQuery.chatbox.css">
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
<script type="text/javascript" src="js/jQuery.chatbox.js"></script>
<script type="text/javascript">
	$(function(){
	    /** For Demo **/
	
	    $.chatbox.globalOptions = {
	        id:99999,
	        user:'Moi',
	        debug:true
	    }
	    
	    $('#do-chat').click(function(){
	        $.chatbox({
	            id:$("#chatbox-id").val(),
	            user:$("#chatbox-user").val(),
	            title:'Chat with '+$("#chatbox-user").val()+'(ID:'+$("#chatbox-id").val()+')'
	        });
	    });

	    $('#set-show').click(function(){
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
	    });
	});
</script>
</body>

</html>