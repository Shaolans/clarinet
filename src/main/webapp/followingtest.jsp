<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Followertest</title>
</head>
<body>
<form action="http://localhost:8080/follow" method="post">
id friend: <input type="text" name="followed"><br>
<button type="submit">Submit</button>
<button type="submit" formmethod="post">Submit using POST</button>

</form>
</body>
</html>