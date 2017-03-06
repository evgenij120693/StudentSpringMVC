<%--
  Created by IntelliJ IDEA.
  User: Шмыга
  Date: 23.02.2017
  Time: 12:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="/students/registration">Registration</a>
<div>
    <form action="/students/login" method="post">
        <table>
            <tr>
                <td>Login:</td>
                <td><input type="text" name="login" value=""></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><input type="password" name="password" value=""></td>
            </tr>
         </table>
        <input type="submit" value="Login">
    </form>
</div>
</body>
</html>
