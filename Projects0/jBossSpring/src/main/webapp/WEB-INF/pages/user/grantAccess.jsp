<%--
  Created by IntelliJ IDEA.
  User: RomanR
  Date: 1/28/14
  Time: 3:56 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Grant Access</title>
</head>
<body>
<table width="100%" border="1" cellspacing="0" cellpadding="5">
    <tr>
        <th width="3%">No</th>
        <th width="45%">Title</th>
        <th width="20%">Author</th>
        <th width="20%">Created</th>
        <th width="12%">&nbsp;</th>
    </tr>
    <c:choose>
        <c:when test="${articles != null}">
            <c:forEach var="article" items="${articles}" varStatus="counter">
                <tr>
                    <td>${counter.index + 1}</td>
                    <td>${article.title}</td>
                    <td>${article.author}</td>
                    <td>${article.created}</td>
                    <td>
                        <div align="center"><a href="viewEditAccess?id=${article.id}">grant access</a></div>
                    </td>
                </tr>
            </c:forEach>
        </c:when>
    </c:choose>
</table
<p>
    <a href="search"><h3>back to Search</h3></a>
</p>

<p>
    <a href="<c:url value="/j_spring_security_logout" />"> Logout</a>
</p>

</body>
</html>
