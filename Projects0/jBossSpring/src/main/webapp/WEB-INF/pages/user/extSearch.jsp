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
    <title>Extended Search form</title>
</head>
<body>
<h2> docStore extended search </h2>
<p></p>
<h3> Please fill in your search criteria </h3>

<p>
    <form:form modelAttribute="extQuery" method="post" action="submitExtSearch">

<table>
    <tr>
        <td>author</td>
        <td><form:input path="author"/></td>
    </tr>
    <tr>
        <td>title</td>
        <td><form:input path="title"/></td>
    </tr>
    <tr>
        <td>subject</td>
        <td><form:input path="subject"/></td>
    </tr>
    <tr>
        <td>keywords</td>
        <td><form:input path="keywords"/></td>
    </tr>
    <tr>
        <td>text</td>
        <td><form:input path="text"/></td>
    </tr>
    <tr>
        <td>language</td>
        <td><form:select path="idLang">
            <form:option value="${languages.idLang}" label="${languages.language}"/>
            <form:options items="${languagesMap}"/> </form:select></td>
    </tr>
    <tr>
        <td>article type</td>
        <td><form:select path="idArticleType">
            <form:option value="${articleType.idArticleType}" label="${articleType.type}"/>
            <form:options items="${articleTypeMap}"/> </form:select></td>
    </tr>
    <tr>
        <td>created day (dd)</td>
        <td><form:input path="createdDay"/></td>
        <td>month (mm)</td>
        <td><form:input path="createdMonth"/></td>
        <td>year (yyyy)</td>
        <td><form:input path="createdYear"/></td>
        <%--<td><span style="color:red"><form:errors path="created" /></span></td>--%>
    </tr>
    <tr>
        <td>modified day (dd)</td>
        <td><form:input path="modifiedDay"/></td>
        <td>month (mm)</td>
        <td><form:input path="modifiedMonth"/></td>
        <td>year (yyyy)</td>
        <td><form:input path="modifiedYear"/></td>
        <%--<td><span style="color:red"><form:errors path="modified" /></span></td>--%>
    </tr>
    <tr>
        <td>paper Size</td>
        <td><form:input path="paperSize"/></td>
    </tr>
    <tr>
        <td>number Of Pages</td>
        <td><form:input path="numberOfPages"/></td>
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
