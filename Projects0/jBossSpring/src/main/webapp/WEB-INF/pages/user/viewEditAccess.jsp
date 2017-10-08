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
    <title>View / Edit Access</title>
</head>
<body>

<h2>Please edit your article info</h2>

<form:form modelAttribute="Shared" method="post" action="submitEditAccess">
    <table>
        <tr>
            <td><form:label path="id">ID</form:label></td>
            <td><form:input path="id" size="10"/></td>
        </tr>
        <tr>
            <td><form:label path="grantee">Grantee name</form:label></td>
            <td><form:input path="grantee" size="50"/></td>
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
