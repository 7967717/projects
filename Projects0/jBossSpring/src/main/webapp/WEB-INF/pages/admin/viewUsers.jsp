<%--
  Created by IntelliJ IDEA.
  User: RomanR
  Date: 1/20/14
  Time: 11:37 AM
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<table>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Password</th>
        <th>Enabled</th>
        <th>Role</th>
    </tr>
    <c:forEach items="${users}" var="user">
        <tr>
            <td>${user.userId}</td>
            <td>${user.username}</td>
            <td>${user.password}</td>
            <td>${user.enabled}</td>
            <td>${user.role}</td>
        </tr>
    </c:forEach>
</table>
<p>

</body>
</html>
