<%--
  Created by IntelliJ IDEA.
  User: Шмыга
  Date: 23.02.2017
  Time: 21:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add student</title>
</head>
<body>
<form action="/students/add" method="post">
    <input type="hidden" name="id" value="">
    <table style = "border: 1px solid grey; box-shadow: 0 0 3px green; margin:10px; padding:10px;">
        <tr>
            <td>Name</td>
            <td><input type="text" name="name" value=""></td>
        </tr>
        <tr>
            <td>Bith_day</td>
            <td><input type="date" name="bith_date" value=""></td>
        </tr>
        <tr>
            <td>Sex</td>
            <td>
                <select name="sex">
                    <option value="w"  selected  >Women</option>
                    <option value="m" > Men</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>Id group</td>
            <td><input type="text" name="id_group" value=""></td>
        </tr>
    </table>
    <input style="margin:10px;" type="submit" value="Save">
</form>
</body>
</html>
