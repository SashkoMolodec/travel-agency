<%--
  Created by IntelliJ IDEA.
  User: sanyk
  Date: 21.08.2020
  Time: 15:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Management</title>
</head>
<body>
<table border="1" cellpadding="5">
            <tr>
                <th>Name</th>
                <th>Password</th>
                <th>Roles</th>
            </tr>
            <c:forEach items="${users}" var="user">
            <tr>
                <td>${user.username}</td>
                <td>${user.password}</td>
                <td>${user.roles}</td>
            </tr>
            </c:forEach>
        </table>
</body>
</html>
