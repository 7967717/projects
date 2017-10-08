<%--
  Created by IntelliJ IDEA.
  User: RomanR
  Date: 2/3/14
  Time: 6:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Request Access</title>
</head>
<body>

<h2>Sorry, you do not have access rights to the article(</h2>
<h2>You can ask owner to grant access</h2>

<form:form modelAttribute="Shared" method="post" action="requestAccess">
    <table>
        <tr>
            <td><form:label path="id">ID</form:label></td>
            <td><form:input path="id" size="10"/></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Request"/>
            </td>
        </tr>
    </table>
</form:form>

</body>
</html>
