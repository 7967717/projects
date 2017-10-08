<%--
  Created by IntelliJ IDEA.
  User: RomanR
  Date: 2/3/14
  Time: 6:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> Edit Metadata</title>
</head>
<body>

<h2>Please edit your article info</h2>

<form:form modelAttribute="newArticle" method="post" action="submitEditMeta">
    <table>
        <tr>
            <td><form:label path="id">ID</form:label></td>
            <td><form:input readonly="true" path="id" size="10"/></td>
        </tr>
        <tr>
            <td><form:label path="author">Author</form:label></td>
            <td><form:input path="author" size="50"/></td>
        </tr>
        <tr>
            <td><form:label path="subject">Subject</form:label></td>
            <td><form:input path="subject" size="50"/></td>
        </tr>
        <tr>
            <td><form:label path="title">Title</form:label></td>
            <td><form:input path="title" size="100%"/></td>
        </tr>
        <tr>
            <td><form:label path="keywords">Keywords</form:label></td>
            <td><form:input path="keywords" size="100%"/></td>
        </tr>
        <tr>
            <td><form:label path="idArticleType">Article Type</form:label></td>
            <td><form:select path="idArticleType">
                <form:option value="${newArticle.idArticleType}" label="${newArticle.type}"/>
                <form:options items="${articleTypeMap}"/> </form:select></td>
            <td><span style="color:red"><form:errors path="idArticleType" /></span></td>
        </tr>
        <tr>
            <td><form:label path="idLang">Language</form:label></td>
            <td><form:select path="idLang">
                <form:option value="${newArticle.idLang}" label="${newArticle.language}"/>
                <form:options items="${languagesMap}"/> </form:select></td>
            <td><span style="color:red"><form:errors path="idLang" /></span></td>
        </tr>
        <tr>
            <td><form:label path="idSharedType">Choose article access</form:label></td>
            <td><form:select path="idSharedType">
                <form:option value="${newArticle.idSharedType}" label="${newArticle.sharedType}"/>
                <form:options items="${sharedTypeMap}"/> </form:select></td>
            <td><span style="color:red"><form:errors path="idSharedType" /></span></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Submit"/>
            </td>
        </tr>
    </table>
</form:form>

</body>
</html>
