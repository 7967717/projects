<%--
  Created by IntelliJ IDEA.
  User: RomanR
  Date: 1/25/14
  Time: 3:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Please fill in the form to register</title>
</head>
<body>
<form:form method="post" action="register" modelAttribute="users">

    <table>
        <tr>
            <td>Your username: </td>
            <td><form:input path="username"/></td>
            <td><span style="color:red"><form:errors path='username' /></span></td>
            <td><span style="color:red">${userExist}</span></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><form:input type="password" path="password"/></td>
            <td><span style="color:red"><form:errors path='password' /></span></td>
        </tr>
        <tr>
            <td>Password confirmation</td>
            <td><form:input type="password" path="passwordConfirmation"/></td>
            <td><span style="color:red"><form:errors path='passwordConfirmation' /></span></td>
            <td><span style="color:red">${password}</span></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Register"/>
            </td>
        </tr>
    </table>

</form:form>

</body>
</html>
