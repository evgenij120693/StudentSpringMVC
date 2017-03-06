<%@ page import="ru.svetozarov.models.pojo.Student" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit student</title>
</head>
<body>
<h1>Edit student</h1>
<%
    Student student = (Student) request.getAttribute("student");
%>

<form action="/students/edit" method="post">
    <input type="hidden" name="id" value="<c:out value="${student.getId()}"></c:out>">
    <table style = "border: 1px solid grey; box-shadow: 0 0 3px green; margin:10px; padding:10px;">
        <tr>
            <td>Name</td>
            <td><input type="text" name="name" value="<c:out value="${student.getName()}"></c:out>"></td>
        </tr>
        <tr>
            <td>Bith_day</td>
            <td><input type="date" name="bith_date" value="<c:out value="${student.getBirth_date()}"></c:out>"></td>
        </tr>
        <tr>
            <td>Sex</td>
            <td>
                <select name="sex">
                    <option value="w" <%if (student.getSex().equals("w")) {%> selected  <%}%>>Women</option>
                    <option value="m" <%if (student.getSex().equals("m")) {%> selected  <%}%>> Men</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>Id group</td>
            <td><input type="text" name="id_group" value="<c:out value="${student.getIdGroup()}"></c:out>"></td>
        </tr>
    </table>
    <input style="margin:10px;" type="submit" value="Save">
</form>
</body>
</html>
