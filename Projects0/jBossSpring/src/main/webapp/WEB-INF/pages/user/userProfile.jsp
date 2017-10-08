<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>
<h1>User Profile</h1>

<h2>${message}</h2>

<%
    out.println("Your IP address is " + request.getRemoteAddr());
%>

<p>
    Today's date: <%= (new java.util.Date()).toLocaleString()%>
</p>

<p>
<a href="addArticle"><h3>add / view your Articles</h3></a>
</p>

<p>
    <a href="grantAccess"><h3>grant Access to your articles</h3></a>
</p>

<p>
    <a href="search"><h3>back to Search</h3></a>
</p>

<p>
    <a href="<c:url value="/j_spring_security_logout" />"> Logout</a>
</p>

</body>
</html>