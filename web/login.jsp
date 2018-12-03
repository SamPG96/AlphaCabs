<%-- 
    Document   : login
    Created on : 09-Nov-2018, 00:43:37
    Author     : 
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link rel="stylesheet" type="text/css" href="stylesheet.css">
        <title>Login</title>
    </head>
    <jsp:include page="/common/head.jsp"/>
    
       <div class="main">
        <h2>Login:</h2>
        <form method="POST" action="LoginServlet.do">
            <table>
                
                                <tr>
                    <td>Username:</td>
                    <td><input type="text" name="username"/></td>
                </tr>
               
                <tr>
                    <td>Password:</td>
                    <td><input type="password" name="password"/></td>
                </tr>
                                <tr> 
                    <td> <input type="submit" value="Login"/></td>
                </tr>
            </table>
        </form>
        
        <div class="errMessage"><%=((String)(request.getAttribute("errMsg"))!=null)?(String)(request.getAttribute("errMsg")):""%></div>
    </div>
    
    
</html>

