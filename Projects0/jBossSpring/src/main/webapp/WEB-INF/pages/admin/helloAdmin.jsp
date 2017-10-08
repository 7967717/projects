<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>
<h1>Admin site</h1>

<h2>${message}</h2>
<%
    out.println("Your IP address is " + request.getRemoteAddr());
%>
<p>
    Today's date: <%= (new java.util.Date()).toLocaleString()%>
</p>

<a href="viewUsers"><h3>view Users</h3></a>

<p>
    <a href="<c:url value="/j_spring_security_logout" />"> Logout</a>
</p>

</body>
</html>