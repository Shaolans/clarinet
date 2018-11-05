<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Tomcat WebSocket Chat</title>
        <script>
        	var url = "ws://" + location.hostname + ":" + location.port + "/clarinet/wschat";
            var ws = new WebSocket(url);
            ws.onopen = function(){
            };
            ws.onmessage = function(message){
                document.getElementById("chatlog").textContent += message.data + "\n";
            };
            function postToServer(){
                ws.send(document.getElementById("msg").value);
                document.getElementById("msg").value = "";
            }
            function closeConnect(){
                ws.close();
            }
        </script>
    </head>
    <body>
        <textarea id="chatlog" readonly></textarea><br/>
        <input id="msg" type="text" />
        <button type="submit" id="sendButton" onClick="postToServer()">Send!</button>
        <button type="submit" id="sendButton" onClick="closeConnect()">End</button>
    </body>
</html>