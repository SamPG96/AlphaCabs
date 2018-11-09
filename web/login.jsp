<%-- 
    Document   : login
    Created on : 09-Nov-2018, 00:43:37
    Author     : yuugy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link rel="stylesheet" type="text/css" href="stylesheet.css">
        <title>Login</title>
    </head>
    
    <jsp:include page="head.jsp"/>
    <div class="main">
        <h2>Login:</h2>
        <form method="POST" action="Update.do">     <%--CHANGE ME --%>
            <table>
                <tr>
                    <th></th>
                    <th>Please provide your following details</th>
                </tr>
                <tr>
                    <td>Username:</td>
                    <td><input type="text" name="username"/></td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><input type="password" name="password"/></td>
                </tr>
                                <tr> 
                    <td> <input type="submit" value="Submit"/></td>
                </tr>
            </table>
        </form>
        
        <%=((String)(request.getAttribute("msg"))!=null)?(String)(request.getAttribute("msg")):""%> <%--CHANGE ME --%>
    </div>
    
    <jsp:include page="foot.jsp"/>
</html>
