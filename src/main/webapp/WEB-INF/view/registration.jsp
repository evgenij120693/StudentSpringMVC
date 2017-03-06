<%--
  Created by IntelliJ IDEA.
  User: Шмыга
  Date: 23.02.2017
  Time: 14:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>
    <form action="/students/registration" method="post">
        <label for="login">Login:</label>
        <input type="text" name="login" value=""><br>
        <label for="password">Password:</label>
        <input type="password" name="password" value="">
        <input type="submit" value="Login">
    </form>
</div>

</body>
</html>
