<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <c:url var="logoutUrl" value="/j_spring_security_check"/>
    <form action="${logoutUrl}" method="post">
        <table>
            <tr>
                <td>Login:</td>
                <td><input type="text" name="j_username" value=""></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><input type="password" name="j_password" value=""></td>
            </tr>
         </table>
        <input type="submit" value="Login">
    </form>
</div>
</body>
</html>
