<%--
  Created by IntelliJ IDEA.
  User: RomanR
  Date: 2/3/14
  Time: 6:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>View Metadata</title>
</head>
<body>

<h2>View your article info</h2>

    <table>
        <tr>
            <td>ID</td>
            <td>${newArticle.id}</td>
        </tr>
        <tr>
            <td>Author</td>
            <td>${newArticle.author}</td>
        </tr>
        <tr>
            <td>Subject</td>
            <td>${newArticle.subject}</td>
        </tr>
        <tr>
            <td>Title</td>
            <td>${newArticle.title}</td>
        </tr>
        <tr>
            <td>Keywords</td>
            <td>${newArticle.keywords}</td>
        </tr>
        <tr>
            <td>Article Type</td>
            <td>${newArticle.type}</td>
        </tr>
        <tr>
            <td>Language </td>
            <td>${newArticle.language}</td>
        </tr>
        <tr>
            <td>Shared Type </td>
            <td>${newArticle.sharedType}</td>
        </tr>
        <tr>
            <td>Created </td>
            <td>${newArticle.created}</td>
        </tr>
        <tr>
            <td>Modified </td>
            <td>${newArticle.modified}</td>
        </tr>
        <tr>
            <td>Status </td>
            <td>${newArticle.status}</td>
        </tr>
        <tr>
            <td>File Name </td>
            <td>${newArticle.fileName}</td>
        </tr>
        <tr>
            <td>File Size </td>
            <td>${newArticle.fileSize}</td>
        </tr>
        <tr>
            <td>Page Size </td>
            <td>${newArticle.pageSize}</td>
        </tr>
        <tr>
            <td>Number Of Pages </td>
            <td>${newArticle.numberOfPages}</td>
        </tr>
    </table>

<p>
    <a href="addArticle"><h3>back to your articles list</h3></a>
</p>
<p>
    <a href="<c:url value="/j_spring_security_logout" />"> Logout</a>
</p>

</body>
</html>
