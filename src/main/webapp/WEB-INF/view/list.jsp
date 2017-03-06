<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>List students</h1>

    <table border="2">
        <tr>
            <th>Имя студента</th>
            <th>День рождения</th>
            <th>Пол</th>
            <th>Id группы</th>
            <th>Edit</th>
        </tr>
        <c:forEach items="${list}" var="student">
            <tr>
                <td><c:out value="${student.getName()}"></c:out></td>
                <td><c:out value="${student.getBirth_date()}"></c:out></td>
                <td><c:out value="${student.getSex()}"></c:out></td>
                <td><c:out value="${student.getIdGroup()}"></c:out></td>
                <td><a href="/students/edit?id=<c:out value="${student.getId()}"></c:out>">edit</a>
                <a href="/students/delete?id=<c:out value="${student.getId()}"></c:out>">delete</a></td>
            </tr>
        </c:forEach>
    </table>
<a href="/students/add">Add student</a>
<h2><a href="/students/logout">Exit</a></h2>

</body>
</html>
