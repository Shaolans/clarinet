<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Followertest</title>
</head>
<body>
<form action="/follow" method="post">
id friend to follow : <input type="text" name="followed"><br>
<button type="submit" formmethod="post">Submit</button>
</form>
<form action="/follow" method="get">
GET DONT PUT ANYTHING: <input type="text" name="followed"><br>
<button type="submit" formmethod="get">Sumbit check</button>
</form>
<form action="/unfollow" method="post">
id friend to unfollow: <input type="text" name="followed"><br>
<button type="submit" formmethod="post">Sumbit Unfollow</button>
</form>

</body>
</html>