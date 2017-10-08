<%-- 
    Document   : login
    Created on : Jul 24, 2013, 9:01:09 AM
    Author     : romanrudenko
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>ePouch Login</title>
    </head>
    <body>
                <h3>ePouch Login</h3>
        <hr/>       
        <form name="loginForm" method="POST" action="controller">
            <input type="hidden" name="command" value="Login">
               Login:<br/>
               <input type="text" name="login" value=""><br/>
               Password:<br/>
               <input type="password" name="password" value=""><br/>
               <input type="submit" value="Enter">
        </form>
    </body>
</html>
