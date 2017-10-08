<%--
  Created by IntelliJ IDEA.
  User: RomanR
  Date: 2/13/14
  Time: 10:53 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Search form</title>
</head>
<body>
<h2> docStore search </h2>
<p>
<a href="extSearch"><h3>go to extended search</h3></a>
</p>
<p>
    <form:form modelAttribute="query" method="post" action="submitSearch">

        <table>
            <tr>
                <td><form:input path="query"/></td>
            </tr>
            <tr>
                <td colspan="1">
                    <input type="submit" value="Search"/>
                </td>
            </tr>
        </table>

    </form:form>
</p>
<p>

</p>
<p>
<table width="100%" border="1" cellspacing="0" cellpadding="5">
    <tr>
        <th width="3%">No</th>
        <th width="35%">Title</th>
        <th width="22%">Author</th>
        <th width="8%">Origin</th>
        <th width="10%">Shared Type</th>
        <th width="10%">Owner</th>
        <th width="12%">&nbsp;</th>
    </tr>
    <c:choose>
        <c:when test="${articles != null}">
            <c:forEach var="article" items="${articles}" varStatus="counter">
                <tr>
                    <td>${counter.index + 1}</td>
                    <td>${article.title}</td>
                    <td>${article.author}</td>
                    <td>${article.duplicate}</td>
                    <td>${article.sharedType}</td>
                    <td>${article.owner}</td>
                    <td>
                        <div align="center"><a href="readArticle?id=${article.id}">Read</a> /
                            <a href="downloadArticle?id=${article.id}">Download</a></div>
                    </td>
                </tr>
            </c:forEach>
        </c:when>
    </c:choose>
</table>
</p>

<a href="userProfile"><h3>go to User profile</h3></a>

<p>
    <a href="<c:url value="/j_spring_security_logout" />"> Logout</a>
</p>

</body>
</html>
